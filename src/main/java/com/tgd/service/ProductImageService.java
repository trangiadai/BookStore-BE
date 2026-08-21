package com.tgd.service;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.tgd.dao.mappers.OrphanedFileMapper;
import com.tgd.dto.mappers.ProductImageMapperDTO;
import com.tgd.entity.Category;
import com.tgd.entity.OrphanedFile;
import com.tgd.entity.ProductImage;
import com.tgd.enums.OrphanedFileStatus;
import com.tgd.repository.ProductImageRepository;

@Service
public class ProductImageService {
	private final ProductImageRepository productImageRepository;
	private final CloudinaryService cloudinaryService;
	private final OrphanedFileMapper orphanedFileMapper; // Added for Outbox Pattern
	private final ProductImageTxService productImageTxService;

	@Transactional
	public int softDeleteProductImage(Long productImageId) {
		ProductImage productImage = getProductImageById(productImageId);
		if (productImage == null) {
			throw new IllegalArgumentException("Not found the product image with id: " + productImageId);
		}
		return productImageRepository.softDeleteProductImage(productImageId);
	}

	public int softDeleteImagesByProductId(Long productId) {
		return productImageRepository.softDeleteImagesByProductId(productId);
	}

	@Transactional
	public int hardDeleteProductImage(Long productImageId) {
		ProductImage productImage = getProductImageById(productImageId);

		if (productImage.getPublicId() != null) {
			pushToOutboxQueue(productImage.getPublicId());
		}

		return productImageRepository.hardDeleteProductImage(productImageId);
	}

	@Transactional
	public int hardDeleteImagesByProductId(Long productId) {
		// Fetch ALL images (including soft-deleted ones)
		List<ProductImage> images = productImageRepository.getAllImagesByProductId(productId);
		for (ProductImage img : images) {
			if (img.getPublicId() != null) {
				pushToOutboxQueue(img.getPublicId());
			}
		}

		return productImageRepository.hardDeleteImagesByProductId(productId);
	}

	public ProductImage getProductImageById(Long productImageId) {
		ProductImage productImage = productImageRepository.getProductImageById(productImageId).orElseThrow(() -> new IllegalArgumentException("Not found the product image with id: " + productImageId));
		
		return productImage;
	}

	public List<ProductImage> getAllImagesByProductId(Long productId) {
		return productImageRepository.getAllImagesByProductId(productId);
	}

	// Main entry point - NO @Transactional here (avoids holding DB connection
	// during HTTP upload)
	public Set<ProductImage> createProductImage(Set<MultipartFile> productImages, Long productId) {
		if (productImages == null || productImages.isEmpty()) {
			return Collections.emptySet();
		}

		Set<ProductImage> uploadedImages = new HashSet<>();

		// STEP 1: Upload to Cloudinary (HTTP Network I/O)
		for (MultipartFile file : productImages) {
			if (file != null && !file.isEmpty()) {
				try {
					ProductImage image = uploadToCloudinary(file);
					uploadedImages.add(image);
				} catch (IOException e) {
					// Network upload failed mid-way -> immediate cleanup of uploaded files
					rollbackCloudinaryUploads(uploadedImages);
					throw new RuntimeException("Image upload failed", e);
				}
			}
		}

		// STEP 2: Persist to DB atomically inside a Spring Proxy transaction
		try {
			return productImageTxService.saveAllImagesToDb(uploadedImages, productId);
		} catch (Exception e) {
			// DB insert failed -> DB automatically rolled back ALL inserted rows.
			// Queue ALL Cloudinary public_ids for background deletion.
			for (ProductImage img : uploadedImages) {
				if (img.getPublicId() != null) {
					pushToOutboxQueue(img.getPublicId());
				}
			}
			throw new RuntimeException("Failed to save product images to database", e);
		}
	}

	public ProductImage uploadToCloudinary(MultipartFile rawProductImage) throws IOException {
		Map uploadResult = cloudinaryService.uploadFile(rawProductImage, "products");
		return ProductImageMapperDTO.toProductImage(uploadResult);
	}

	private void rollbackCloudinaryUploads(Set<ProductImage> images) {
		for (ProductImage img : images) {
			if (img.getPublicId() != null) {
				try {
					cloudinaryService.deleteFile(img.getPublicId());
				} catch (IOException e) {
					pushToOutboxQueue(img.getPublicId());
				}
			}
		}
	}

	private void pushToOutboxQueue(String publicId) {
		OrphanedFile file = new OrphanedFile();
		file.setPublicId(publicId);
		file.setStatus(OrphanedFileStatus.PENDING.name());
		file.setRetryCount(0);
		orphanedFileMapper.insert(file);
	}

	public ProductImageService(ProductImageRepository productImageRepository, CloudinaryService cloudinaryService,
			OrphanedFileMapper orphanedFileMapper, ProductImageTxService productImageTxService) {
		super();
		this.productImageRepository = productImageRepository;
		this.cloudinaryService = cloudinaryService;
		this.orphanedFileMapper = orphanedFileMapper;
		this.productImageTxService = productImageTxService;
	}

}
