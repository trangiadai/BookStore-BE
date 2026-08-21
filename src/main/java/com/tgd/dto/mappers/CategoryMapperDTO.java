package com.tgd.dto.mappers;

import com.tgd.dto.request.CategoryRequestDTO;
import com.tgd.dto.response.CategoryResponseDTO;
import com.tgd.entity.Category;

public class CategoryMapperDTO {
	public static CategoryResponseDTO toCategoryResponse(Category category) {
		if (category == null) {
			return null;
		}

		CategoryResponseDTO categoryResponse = new CategoryResponseDTO();
		categoryResponse.setId(category.getId());
		categoryResponse.setName(category.getName());
		categoryResponse.setDescription(category.getDescription());
		categoryResponse.setCreatedAt(category.getCreatedAt());
		categoryResponse.setUpdatedAt(category.getUpdatedAt());

		return categoryResponse;
	}

	public static Category toCategory(CategoryRequestDTO categoryRequest) {
		if (categoryRequest == null) {
			return null;
		}

		Category category = new Category();
		category.setName(categoryRequest.getName());
		category.setDescription(categoryRequest.getDescription());

		return category;
	}
}
