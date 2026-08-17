package com.tgd.dao.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.tgd.entity.ProductImage;

@Mapper
public interface ProductImageMapper {
	ProductImage getProductImageById(Map<String, Object> param);

	int createProductImage(Map<String, Object> param);
	
	int softDeleteProductImage(Map<String, Object> param);
	
	int softDeleteImagesByProductId(Map<String, Object> param);
	
	int hardDeleteProductImage(Map<String, Object> param);
	
	int hardDeleteImagesByProductId(Map<String, Object> param);
	
	List<ProductImage> getAllImagesByProductId(Map<String, Object> param);
}
