package com.tgd.dto.request.payment;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class AddToCartRequest {
	@NotNull(message = "Product ID is required")
	@Positive(message = "Product ID must be positive")
	private Long productId;

	@NotNull(message = "Quantity is required")
	@Min(value = 1, message = "Quantity must be at least 1")
	@Max(value = 99, message = "The maximun quantity can add is 99")
	private Integer quantity;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public AddToCartRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AddToCartRequest(Long productId, Integer quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
	}

}