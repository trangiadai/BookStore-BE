package com.tgd.dto.request;

public class ReviewUpdateEvent {
	private Long productId;
	private Integer oldRating;
	private Integer newRating;

	public ReviewUpdateEvent(Long productId, Integer oldRating, Integer newRating) {
		super();
		this.productId = productId;
		this.oldRating = oldRating;
		this.newRating = newRating;
	}

	public ReviewUpdateEvent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getOldRating() {
		return oldRating;
	}

	public void setOldRating(Integer oldRating) {
		this.oldRating = oldRating;
	}

	public Integer getNewRating() {
		return newRating;
	}

	public void setNewRating(Integer newRating) {
		this.newRating = newRating;
	}
}
