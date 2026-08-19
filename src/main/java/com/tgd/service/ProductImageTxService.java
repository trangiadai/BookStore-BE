package com.tgd.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tgd.entity.ProductImage;
import com.tgd.repository.ProductImageRepository;

@Component
public class ProductImageTxService {

	private final ProductImageRepository productImageRepository;

	public ProductImageTxService(ProductImageRepository productImageRepository) {
		super();
		this.productImageRepository = productImageRepository;
	}

	// Transactional Helper - MUST be public and called from outside (ProductImageService) or via proxy to solve Self-Invocation Problem bypass transactional
	@Transactional
	public Set<ProductImage> saveAllImagesToDb(Set<ProductImage> uploadedImages, Long productId) {
		Set<ProductImage> savedImages = new HashSet<>();
		for (ProductImage img : uploadedImages) {
			img.setProductId(productId);
			Long id = productImageRepository.createProductImage(img).longValue();
			img.setId(id);
			savedImages.add(img);
		}
		return savedImages;
	}
}
