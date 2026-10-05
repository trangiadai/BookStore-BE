package com.tgd.dto.request;

public class ReviewEvent {
	private Long productId;
	private Long accountId;
	private Long orderItemId;
	private Integer rating;
	private String comment;

	public ReviewEvent(Long productId, Long accountId, Long orderItemId, Integer rating, String comment) {
		super();
		this.productId = productId;
		this.accountId = accountId;
		this.orderItemId = orderItemId;
		this.rating = rating;
		this.comment = comment;
	}

	public ReviewEvent() {
		super();
		// TODO Auto-generated constructor stub
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
}
