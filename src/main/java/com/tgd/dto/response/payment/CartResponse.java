package com.tgd.dto.response.payment;

import java.math.BigDecimal;
import java.util.List;

public class CartResponse {
	private Long cartId;
	private List<CartItemResponse> items;
	private BigDecimal grandTotal;

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public List<CartItemResponse> getItems() {
		return items;
	}

	public void setItems(List<CartItemResponse> items) {
		this.items = items;
	}

	public BigDecimal getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}

	public CartResponse(Long cartId, List<CartItemResponse> items, BigDecimal grandTotal) {
		super();
		this.cartId = cartId;
		this.items = items;
		this.grandTotal = grandTotal;
	}

	public CartResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
}