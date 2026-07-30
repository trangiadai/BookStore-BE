package com.tgd.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tgd.dao.mappers.ProductMapper;
import com.tgd.entity.Product;

@Repository
public class ProductRepository {
	private final ProductMapper productMapper;

	public List<Product> getAllProducts() {
		
		return productMapper.getAllProducts();
	}
	
	public Number createProduct() {
		
		return productMapper.getAllProducts();
	}

	public ProductRepository(ProductMapper productMapper) {
		super();
		this.productMapper = productMapper;
	}

}
