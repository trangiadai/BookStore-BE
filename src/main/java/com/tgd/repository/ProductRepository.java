package com.tgd.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tgd.dao.mappers.ProductMapper;
import com.tgd.entity.Product;

@Repository
public class ProductRepository {
	private final ProductMapper productMapper;
	
	public Product getProductById(Long id) {
		Map<String, Object> param = new HashMap<>();
		param.put("id", id);
		
		return productMapper.getProductById(param);
	}

	public List<Product> getAllProducts() {
		
		return productMapper.getAllProducts();
	}
	
	public Number createProduct(Product product) {
		Map<String, Object> param = new HashMap<>();
		param.put("name", product.getName());
		param.put("importPrice", product.getImportPrice());
		param.put("sellingPrice", product.getSellingPrice());
		param.put("quantity", product.getQuantity());
		param.put("description", product.getDescription());
		param.put("productCategory", product.getProductCategory());
		productMapper.createProduct(param);
		
		return (Number) param.get("id");
	}

	public ProductRepository(ProductMapper productMapper) {
		super();
		this.productMapper = productMapper;
	}

}
