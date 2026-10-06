package com.tgd.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ReviewUpdateRequest {
	@NotNull(message = "Rating is required")
	@Min(value = 1, message = "Rating must be at least 1")
	@Max(value = 5, message = "Rating cannot exceed 5")
	private Integer rating;

	@Size(max = 1000, message = "Comment must not exceed 1000 characters")
	private String comment;

	public ReviewUpdateRequest(Integer rating, String comment) {
		super();
		this.rating = rating;
		this.comment = comment;
	}

	public ReviewUpdateRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
