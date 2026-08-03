package com.tgd.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tgd.dto.mappers.ProductImageMapperDTO;
import com.tgd.entity.ProductImage;
import com.tgd.repository.ProductImageRepository;

@Service
public class ProductImageService {
	private final ProductImageRepository productImageRepository;
	private final CloudinaryService cloudinaryService;

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

	public ProductImageService(ProductImageRepository productImageRepository, CloudinaryService cloudinaryService) {
		super();
		this.productImageRepository = productImageRepository;
		this.cloudinaryService = cloudinaryService;
	}

}
