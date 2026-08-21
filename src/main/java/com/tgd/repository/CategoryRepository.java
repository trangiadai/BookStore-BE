package com.tgd.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.tgd.dao.mappers.CategoryMapper;
import com.tgd.entity.Category;

@Repository
public class CategoryRepository {
	private final CategoryMapper categoryMapper;

	public List<Category> getAllCategories() {
		return categoryMapper.getAllCategories();
	}

	public Optional<Category> getCategoryById(Long id) {
		Map<String, Object> param = new HashMap<>();
		param.put("id", id);

		return categoryMapper.getCategoryById(param);
	}

	public Number createCategory(Category category) {
		Map<String, Object> param = new HashMap<>();
		param.put("name", category.getName());
		param.put("description", category.getDescription());
		categoryMapper.createCategory(param);

		return (Number) param.get("id");
	}

	public int updateCategory(Category category) {
		Map<String, Object> param = new HashMap<>();
		param.put("id", category.getId());
		param.put("name", category.getName());
		param.put("description", category.getDescription());

		return categoryMapper.updateCategory(param);
	}

	public int deleteCategory(Long id) {
		Map<String, Object> param = new HashMap<>();
		param.put("id", id);

		return categoryMapper.deleteCategory(param);
	}

	public CategoryRepository(CategoryMapper categoryMapper) {
		super();
		this.categoryMapper = categoryMapper;
	}

}
