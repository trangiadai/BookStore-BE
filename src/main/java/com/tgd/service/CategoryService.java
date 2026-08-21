package com.tgd.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tgd.dto.mappers.CategoryMapperDTO;
import com.tgd.dto.request.CategoryRequestDTO;
import com.tgd.dto.response.CategoryResponseDTO;
import com.tgd.entity.Category;
import com.tgd.repository.CategoryRepository;

@Service
public class CategoryService {
	private final CategoryRepository categoryRepository;

	public List<CategoryResponseDTO> getAllCategories() {
		return categoryRepository.getAllCategories().stream().map(CategoryMapperDTO::toCategoryResponse)
				.collect(Collectors.toList());
	}

	public CategoryResponseDTO getCategoryById(Long id) {
		Category category = categoryRepository.getCategoryById(id)
				.orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + id));

		return CategoryMapperDTO.toCategoryResponse(category);
	}

	@Transactional
	public CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequest) {
		Category category = CategoryMapperDTO.toCategory(categoryRequest);
		category.setId(categoryRepository.createCategory(category).longValue());

		return CategoryMapperDTO.toCategoryResponse(category);
	}

	@Transactional
	public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO requestDTO) {
		Category existingCategory = categoryRepository.getCategoryById(id)
				.orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + id));

		existingCategory.setName(requestDTO.getName());
		existingCategory.setDescription(requestDTO.getDescription());
		categoryRepository.updateCategory(existingCategory);

		return CategoryMapperDTO.toCategoryResponse(existingCategory);
	}

	@Transactional
	public int deleteCategory(Long id) {
		categoryRepository.getCategoryById(id)
				.orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + id));

		return categoryRepository.deleteCategory(id);
	}

	public CategoryService(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}

}