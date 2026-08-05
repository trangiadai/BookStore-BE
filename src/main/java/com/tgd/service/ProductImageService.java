package com.tgd.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.tgd.dto.mappers.ProductImageMapperDTO;
import com.tgd.entity.ProductImage;
import com.tgd.repository.ProductImageRepository;

@Service
public class ProductImageService {
	private final ProductImageRepository productImageRepository;
	private final CloudinaryService cloudinaryService;
	private final ProductService productService;

	public int deleteProductImage(Long productImageId) {
		ProductImage productImage = getProductImageById(productImageId);

		if (productImage == null) {
			throw new IllegalArgumentException("Not found the product image with id: " + productImageId);
		}

		deleteFromCloudinary(productImage.getPublicId());

		return productImageRepository.deleteProductImage(productImage.getId());
	}

	private ProductImage getProductImageById(Long productImageId) {
		return productImageRepository.getProductImageById(productImageId);
	}

	public Set<ProductImage> createProductImage(Set<MultipartFile> productImages, Long productId) {
		if (productService.getProductById(productId) == null) {
			throw new IllegalArgumentException("Not found the product with id: " + productId);
		}

		Set<ProductImage> savedImages = new HashSet<>();
		Set<ProductImage> uploadedImages = new HashSet<>();
		Set<MultipartFile> rawProductImages = productImages;
		if (rawProductImages != null && !rawProductImages.isEmpty()) {
			for (MultipartFile file : rawProductImages) {
				if (file != null && !file.isEmpty()) {
					try {
						ProductImage image = uploadToCloudinary(file);
						uploadedImages.add(image);
					} catch (IOException e) {
						// Cleanup files uploaded so far if one upload fails
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
				// If DB insert fails, delete files from Cloudinary
				rollbackCloudinaryUploads(uploadedImages);
				throw e;
			}
		}

		return savedImages;
	}

	private void rollbackCloudinaryUploads(Set<ProductImage> images) {
		for (ProductImage img : images) {
			if (img.getPublicId() != null) {
				deleteFromCloudinary(img.getPublicId());
			}
		}
	}

	// STEP 1: HTTP Call to Cloudinary (Run OUTSIDE @Transactional)
	public ProductImage uploadToCloudinary(MultipartFile rawProductImage) throws IOException {
		Map uploadResult = cloudinaryService.uploadFile(rawProductImage, "products");
		return ProductImageMapperDTO.toProductImage(uploadResult);
	}

	public ProductImage saveProductImageToDb(ProductImage productImage, Long productId) {
		productImage.setProductId(productId);
		Long id = productImageRepository.createProductImage(productImage).longValue();
		productImage.setId(id);
		return productImage;
	}

	// Helper method to clean up Cloudinary if DB fails
	public void deleteFromCloudinary(String publicId) {
		try {
			cloudinaryService.deleteFile(publicId);
		} catch (IOException e) {
			// Log warning for manual cleanup if necessary
		}
	}

	public ProductImageService(ProductImageRepository productImageRepository, CloudinaryService cloudinaryService,
			ProductService productService) {
		super();
		this.productImageRepository = productImageRepository;
		this.cloudinaryService = cloudinaryService;
		this.productService = productService;
	}

}
