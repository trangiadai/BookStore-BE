package com.tgd.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryRequestDTO {
	@NotBlank(message = "Category name is required")
	@Size(max = 255, message = "Category name must be under 255 characters")
	private String name;

	@Size(max = 100000, message = "Category name must be under 100000 characters")
	private String description;

	public CategoryRequestDTO(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public CategoryRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
