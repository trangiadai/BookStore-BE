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

	public ProductImage createProductImage(MultipartFile rawProductImage, Long productId) throws IOException {
		Map uploadResult = cloudinaryService.uploadFile(rawProductImage, "products");
		ProductImage productImage = ProductImageMapperDTO.toProductImage(uploadResult);
		productImage.setProductId(productId);
		productImage.setId(productImageRepository.createProductImage(productImage).longValue());

		return productImage;
	}

	public ProductImageService(ProductImageRepository productImageRepository, CloudinaryService cloudinaryService) {
		super();
		this.productImageRepository = productImageRepository;
		this.cloudinaryService = cloudinaryService;
	}

}
