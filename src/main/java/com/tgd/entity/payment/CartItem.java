package com.tgd.entity.payment;

import java.time.LocalDateTime;

public class CartItem {
	private Long id;
	private Long cartId;
	private Long productId;
	private Integer quantity;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public CartItem(Long id, Long cartId, Long productId, Integer quantity, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.cartId = cartId;
		this.productId = productId;
		this.quantity = quantity;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}
