package com.tgd.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tgd.dao.mappers.ProductImageMapper;
import com.tgd.entity.ProductImage;

@Repository
public class ProductImageRepository {
	private final ProductImageMapper productImageMapper;

	public Number createProductImage(ProductImage productImage) {
		Map<String, Object> param = new HashMap<>();
		param.put("url", productImage.getUrl());
		param.put("publicId", productImage.getPublicId());
		param.put("productId", productImage.getProductId());

		productImageMapper.createProductImage(param);
		return (Number) param.get("id");
	}

	public ProductImageRepository(ProductImageMapper productImageMapper) {
		super();
		this.productImageMapper = productImageMapper;
	}

}
