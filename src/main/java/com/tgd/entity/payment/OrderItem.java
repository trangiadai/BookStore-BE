package com.tgd.entity.payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderItem {
	private Long id;
	private Long orderId;
	private Long productId;
	private String productName;
	private BigDecimal unitPrice;
	private Integer quantity;
	private BigDecimal subtotal;
	private LocalDateTime createdAt;

	public OrderItem(Long id, Long orderId, Long productId, String productName, BigDecimal unitPrice, Integer quantity,
			BigDecimal subtotal, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.productId = productId;
		this.productName = productName;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.subtotal = subtotal;
		this.createdAt = createdAt;
	}

	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}
