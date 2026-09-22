package com.tgd.entity.payment;

import java.time.LocalDateTime;
import java.util.List;

public class Cart {
	private Long id;
	private Long accountId;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private List<CartItem> items;

	public Cart(Long id, Long accountId, LocalDateTime createdAt, LocalDateTime updatedAt, List<CartItem> items) {
		super();
		this.id = id;
		this.accountId = accountId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.items = items;
	}

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
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

	public List<CartItem> getItems() {
		return items;
	}

	public void setItems(List<CartItem> items) {
		this.items = items;
	}
}
