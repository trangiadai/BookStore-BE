package com.tgd.dto.mappers;

import com.tgd.dto.request.CategoryRequest;
import com.tgd.dto.response.CategoryResponse;
import com.tgd.entity.Category;

public class CategoryMapperDTO {
	public static CategoryResponse toCategoryResponse(Category category) {
		if (category == null) {
			return null;
		}

		CategoryResponse categoryResponse = new CategoryResponse();
		categoryResponse.setId(category.getId());
		categoryResponse.setName(category.getName());
		categoryResponse.setDescription(category.getDescription());
		categoryResponse.setCreatedAt(category.getCreatedAt());
		categoryResponse.setUpdatedAt(category.getUpdatedAt());

		return categoryResponse;
	}

	public static Category toCategory(CategoryRequest categoryRequest) {
		if (categoryRequest == null) {
			return null;
		}

		Category category = new Category();
		category.setName(categoryRequest.getName());
		category.setDescription(categoryRequest.getDescription());

		return category;
	}
}
