package com.tgd.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.tgd.dao.mappers.ProductImageMapper;
import com.tgd.entity.ProductImage;

@Repository
public class ProductImageRepository {
	private final ProductImageMapper productImageMapper;
	
	public Optional<ProductImage> getProductImageById(Long productImageId) {
		Map<String, Object> param = new HashMap<>();
		param.put("productImageId", productImageId);
		
		return productImageMapper.getProductImageById(param);
	}

	public Number createProductImage(ProductImage productImage) {
		Map<String, Object> param = new HashMap<>();
		param.put("url", productImage.getUrl());
		param.put("publicId", productImage.getPublicId());
		param.put("productId", productImage.getProductId());

		productImageMapper.createProductImage(param);
		return (Number) param.get("id");
	}
	
	public int softDeleteProductImage(Long productImageId) {
		Map<String, Object> param = new HashMap<>();
		param.put("productImageId", productImageId);
		
		return productImageMapper.softDeleteProductImage(param);
	}
	
	public int softDeleteImagesByProductId(Long productId) {
		Map<String, Object> param = new HashMap<>();
		param.put("productId", productId);
		
		return productImageMapper.softDeleteImagesByProductId(param);
	}
	
	public int hardDeleteProductImage(Long productImageId) {
		Map<String, Object> param = new HashMap<>();
		param.put("productImageId", productImageId);
		
		return productImageMapper.hardDeleteProductImage(param);
	}
	
	public int hardDeleteImagesByProductId(Long productId) {
		Map<String, Object> param = new HashMap<>();
		param.put("productId", productId);
		
		return productImageMapper.hardDeleteImagesByProductId(param);
	}
	
	public List<ProductImage> getAllImagesByProductId(Long productId){
		Map<String, Object> param = new HashMap<>();
		param.put("productId", productId);
		
		return productImageMapper.getAllImagesByProductId(param);
	}

	public ProductImageRepository(ProductImageMapper productImageMapper) {
		super();
		this.productImageMapper = productImageMapper;
	}

}
