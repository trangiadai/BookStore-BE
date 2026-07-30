package com.tgd.dao.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tgd.entity.Product;

@Mapper
public interface ProductMapper {
	List<Product> getAllProducts();
}
