package com.tgd.service;

import java.util.Collections;
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
		if (uploadedImages == null || uploadedImages.isEmpty()) {
			return Collections.emptySet();
		}

		Set<ProductImage> savedImages = new HashSet<>();

		// 1. Check if the product already has an active cover image in the database
		boolean hasPrimary = productImageRepository.hasPrimaryImage(productId);

		int orderIndex = 0;
		for (ProductImage img : uploadedImages) {
			img.setProductId(productId);

			// 2. If no primary image exists yet, assign the first uploaded image as primary
			if (!hasPrimary && orderIndex == 0) {
				img.setIsPrimary(true);
			} else {
				img.setIsPrimary(false);
			}

			img.setDisplayOrder(orderIndex);

			// 3. Persist to DB (Ensure repository method accepts isPrimary and
			// displayOrder)
			Long id = productImageRepository.createProductImage(img).longValue();
			img.setId(id);

			savedImages.add(img);
			orderIndex++;
		}

		return savedImages;
	}
}
