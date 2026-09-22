package com.tgd.entity.payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.tgd.enums.OrderStatus;
import com.tgd.enums.PaymentMethod;
import com.tgd.enums.PaymentStatus;

public class Order {
	private Long id;
	private Long accountId;
	private String orderCode;
	private BigDecimal subtotalAmount;
	private BigDecimal shippingFee;
	private BigDecimal totalAmount;
	private OrderStatus orderStatus;
	private PaymentStatus paymentStatus;
	private PaymentMethod paymentMethod;
	private String shippingFullName;
	private String shippingPhoneNumber;
	private String shippingAddress;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	private List<OrderItem> items;

	public Order(Long id, Long accountId, String orderCode, BigDecimal subtotalAmount, BigDecimal shippingFee,
			BigDecimal totalAmount, OrderStatus orderStatus, PaymentStatus paymentStatus, PaymentMethod paymentMethod,
			String shippingFullName, String shippingPhoneNumber, String shippingAddress, LocalDateTime createdAt,
			LocalDateTime updatedAt, List<OrderItem> items) {
		super();
		this.id = id;
		this.accountId = accountId;
		this.orderCode = orderCode;
		this.subtotalAmount = subtotalAmount;
		this.shippingFee = shippingFee;
		this.totalAmount = totalAmount;
		this.orderStatus = orderStatus;
		this.paymentStatus = paymentStatus;
		this.paymentMethod = paymentMethod;
		this.shippingFullName = shippingFullName;
		this.shippingPhoneNumber = shippingPhoneNumber;
		this.shippingAddress = shippingAddress;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.items = items;
	}

	public Order() {
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

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public BigDecimal getSubtotalAmount() {
		return subtotalAmount;
	}

	public void setSubtotalAmount(BigDecimal subtotalAmount) {
		this.subtotalAmount = subtotalAmount;
	}

	public BigDecimal getShippingFee() {
		return shippingFee;
	}

	public void setShippingFee(BigDecimal shippingFee) {
		this.shippingFee = shippingFee;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getShippingFullName() {
		return shippingFullName;
	}

	public void setShippingFullName(String shippingFullName) {
		this.shippingFullName = shippingFullName;
	}

	public String getShippingPhoneNumber() {
		return shippingPhoneNumber;
	}

	public void setShippingPhoneNumber(String shippingPhoneNumber) {
		this.shippingPhoneNumber = shippingPhoneNumber;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
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

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
}
