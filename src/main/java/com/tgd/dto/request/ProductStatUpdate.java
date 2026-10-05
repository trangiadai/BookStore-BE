package com.tgd.dto.request;

public class ProductStatUpdate {
	private Long productId;
	private Integer countIncrement;
	private Integer sumIncrement;

	public ProductStatUpdate(Long productId, Integer countIncrement, Integer sumIncrement) {
		super();
		this.productId = productId;
		this.countIncrement = countIncrement;
		this.sumIncrement = sumIncrement;
	}

	public ProductStatUpdate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getCountIncrement() {
		return countIncrement;
	}

	public void setCountIncrement(Integer countIncrement) {
		this.countIncrement = countIncrement;
	}

	public Integer getSumIncrement() {
		return sumIncrement;
	}

	public void setSumIncrement(Integer sumIncrement) {
		this.sumIncrement = sumIncrement;
	}
}
