package com.tgd.dao.mappers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.tgd.entity.Product;

@Mapper
public interface ProductMapper {
	List<Product> getAllProducts();

	void createProduct(Map<String, Object> param);
	
	int updateProduct(Map<String, Object> param);
	
	Optional<Product> getProductById(Map<String, Object> param);
	
	int softDeleteProduct(Map<String, Object> param);
	
	int hardDeleteProduct(Map<String, Object> param);
}
