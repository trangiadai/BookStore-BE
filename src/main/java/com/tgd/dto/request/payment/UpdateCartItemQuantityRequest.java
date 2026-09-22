package com.tgd.dto.request.payment;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class UpdateCartItemQuantityRequest {
	@NotNull(message = "Quantity is required")
	@Min(value = 1, message = "Quantity must be at least 1")
	private Integer quantity;

	public UpdateCartItemQuantityRequest(Integer quantity) {
		super();
		this.quantity = quantity;
	}

	public UpdateCartItemQuantityRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
