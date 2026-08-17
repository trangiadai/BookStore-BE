package com.tgd.service;

import java.io.IOException;
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
import com.tgd.entity.OrphanedFile;
import com.tgd.entity.ProductImage;
import com.tgd.enums.OrphanedFileStatus;
import com.tgd.repository.ProductImageRepository;

@Service
public class ProductImageService {
	private final ProductImageRepository productImageRepository;
	private final CloudinaryService cloudinaryService;
	private final OrphanedFileMapper orphanedFileMapper; // Added for Outbox Pattern

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
		if (productImage == null) {
			throw new IllegalArgumentException("Not found the product image with id: " + productImageId);
		}

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
		return productImageRepository.getProductImageById(productImageId);
	}

	public List<ProductImage> getAllImagesByProductId(Long productId) {
		return productImageRepository.getAllImagesByProductId(productId);
	}

	@Transactional
	public ProductImage saveProductImageToDb(ProductImage productImage, Long productId) {
		productImage.setProductId(productId);
		productImage.setId(productImageRepository.createProductImage(productImage).longValue());

		return productImage;
	}

	public Set<ProductImage> createProductImage(Set<MultipartFile> productImages, Long productId) {
		Set<ProductImage> savedImages = new HashSet<>();
		Set<ProductImage> uploadedImages = new HashSet<>();

		if (productImages != null && !productImages.isEmpty()) {
			for (MultipartFile file : productImages) {
				if (file != null && !file.isEmpty()) {
					try {
						ProductImage image = uploadToCloudinary(file);
						uploadedImages.add(image);
					} catch (IOException e) {
						rollbackCloudinaryUploads(uploadedImages);
						throw new RuntimeException("Image upload failed", e);
					}
				}
			}

			try {
				for (ProductImage img : uploadedImages) {
					savedImages.add(saveProductImageToDb(img, productId));
				}
			} catch (Exception e) {
				for (ProductImage img : uploadedImages) {
					if (img.getPublicId() != null) {
						pushToOutboxQueue(img.getPublicId());
					}
				}
				throw e;
			}
		}

		return savedImages;
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
			OrphanedFileMapper orphanedFileMapper) {
		this.productImageRepository = productImageRepository;
		this.cloudinaryService = cloudinaryService;
		this.orphanedFileMapper = orphanedFileMapper;
	}

}
