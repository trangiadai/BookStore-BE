package com.tgd.dao.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.tgd.entity.Product;

@Mapper
public interface ProductMapper {
	List<Product> getAllProducts();

	void createProduct(Map<String, Object> param);
	
	Product getProductById(Map<String, Object> param);
}
