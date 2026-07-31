package com.tgd.dao.mappers;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductImageMapper {

	int createProductImage(Map<String, Object> param);
}
