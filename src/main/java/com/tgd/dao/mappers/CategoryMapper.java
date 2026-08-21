package com.tgd.dao.mappers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.tgd.entity.Category;

@Mapper
public interface CategoryMapper {

	List<Category> getAllCategories();

	Optional<Category> getCategoryById(Map<String, Object> param);

	int createCategory(Map<String, Object> param);

	int updateCategory(Map<String, Object> param);

	int deleteCategory(Map<String, Object> param);
}
