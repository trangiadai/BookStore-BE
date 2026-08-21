package com.tgd.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tgd.dto.request.CategoryRequestDTO;
import com.tgd.dto.response.CategoryResponseDTO;
import com.tgd.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	private final CategoryService categoryService;

	@GetMapping
	public List<CategoryResponseDTO> getAllCategories() {
		return categoryService.getAllCategories();
	}

	@GetMapping("/{id}")
	public CategoryResponseDTO getCategoryById(@PathVariable Long id) {
		return categoryService.getCategoryById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CategoryResponseDTO createCategory(@Valid @RequestBody CategoryRequestDTO requestDTO) {
		return categoryService.createCategory(requestDTO);
	}

	@PutMapping("/{id}")
	public CategoryResponseDTO updateCategory(@PathVariable Long id,
			@Valid @RequestBody CategoryRequestDTO requestDTO) {
		return categoryService.updateCategory(id, requestDTO);
	}

	@DeleteMapping("/{id}")
	public int deleteCategory(@PathVariable Long id) {
		return categoryService.deleteCategory(id);
	}

	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}

}