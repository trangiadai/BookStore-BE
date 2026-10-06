package com.tgd.dto.response;

import java.time.LocalDateTime;

public class UserReviewResponse {
	private Long id;
	private Long orderItemId;
	private Integer rating;
	private String comment;
	private Integer editCount;
	private LocalDateTime createdAt;
	private Boolean isEditable;
	private ProductSummary product;

	public UserReviewResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserReviewResponse(Long id, Long orderItemId, Integer rating, String comment, Integer editCount,
			LocalDateTime createdAt, Boolean isEditable, ProductSummary product) {
		super();
		this.id = id;
		this.orderItemId = orderItemId;
		this.rating = rating;
		this.comment = comment;
		this.editCount = editCount;
		this.createdAt = createdAt;
		this.isEditable = isEditable;
		this.product = product;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
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

	public Integer getEditCount() {
		return editCount;
	}

	public void setEditCount(Integer editCount) {
		this.editCount = editCount;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Boolean getIsEditable() {
		return isEditable;
	}

	public void setIsEditable(Boolean isEditable) {
		this.isEditable = isEditable;
	}

	public ProductSummary getProduct() {
		return product;
	}

	public void setProduct(ProductSummary product) {
		this.product = product;
	}

	public static class ProductSummary {
		private Long id;
		private String name;

		public ProductSummary(Long id, String name) {
			super();
			this.id = id;
			this.name = name;
		}

		public ProductSummary() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}
