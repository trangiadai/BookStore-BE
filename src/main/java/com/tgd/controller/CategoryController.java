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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tgd.dto.request.CategoryRequest;
import com.tgd.dto.response.CategoryResponse;
import com.tgd.service.CategoryService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	private final CategoryService categoryService;

	@GetMapping
	public List<CategoryResponse> getAllCategories() {
		return categoryService.getAllCategories();
	}

	@GetMapping("/{id}")
	public CategoryResponse getCategoryById(
			@PathVariable @Positive(message = "Category's id must be greater than 0") Long id) {
		return categoryService.getCategoryById(id);
	}

	@GetMapping("/search")
	public List<CategoryResponse> getCategoryByName(
			@RequestParam @NotBlank(message = "Category name cannot be blank") @Size(max = 100, message = "The maximun length of category's name is 100") String name) {
		return categoryService.getCategoryByName(name);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CategoryResponse createCategory(@Valid @RequestBody CategoryRequest requestDTO) {
		return categoryService.createCategory(requestDTO);
	}

	@PutMapping("/{id}")
	public CategoryResponse updateCategory(@PathVariable Long id,
			@Valid @RequestBody CategoryRequest requestDTO) {
		return categoryService.updateCategory(id, requestDTO);
	}

	@DeleteMapping("/{id}")
	public int deleteCategory(@PathVariable @Positive(message = "Category's id must be greater than 0") Long id) {
		return categoryService.deleteCategory(id);
	}

	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}
}