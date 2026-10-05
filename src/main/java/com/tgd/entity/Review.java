package com.tgd.entity;

import java.time.LocalDateTime;

public class Review {
	private Long id;
	private Long orderItemId;
	private Long productId;
	private Long accountId;
	private Integer rating;
	private String comment;
	private LocalDateTime createdAt;

	public Review(Long id, Long orderItemId, Long productId, Long accountId, Integer rating, String comment,
			LocalDateTime createdAt) {
		super();
		this.id = id;
		this.orderItemId = orderItemId;
		this.productId = productId;
		this.accountId = accountId;
		this.rating = rating;
		this.comment = comment;
		this.createdAt = createdAt;
	}

	public Review() {
		super();
		// TODO Auto-generated constructor stub
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

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}
