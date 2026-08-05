package com.tgd.dao.mappers;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.tgd.entity.ProductImage;

@Mapper
public interface ProductImageMapper {
	ProductImage getProductImageById(Map<String, Object> param);

	int createProductImage(Map<String, Object> param);
	
	int deleteProductImage(Map<String, Object> param);
}
