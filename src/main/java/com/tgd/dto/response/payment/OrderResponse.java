package com.tgd.dto.response.payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.tgd.enums.OrderStatus;
import com.tgd.enums.PaymentMethod;
import com.tgd.enums.PaymentStatus;

public class OrderResponse {
	private Long id;
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
	private List<OrderItemResponse> items;
	private LocalDateTime createdAt;

	public OrderResponse(Long id, String orderCode, BigDecimal subtotalAmount, BigDecimal shippingFee,
			BigDecimal totalAmount, OrderStatus orderStatus, PaymentStatus paymentStatus, PaymentMethod paymentMethod,
			String shippingFullName, String shippingPhoneNumber, String shippingAddress, List<OrderItemResponse> items,
			LocalDateTime createdAt) {
		super();
		this.id = id;
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
		this.items = items;
		this.createdAt = createdAt;
	}

	public OrderResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<OrderItemResponse> getItems() {
		return items;
	}

	public void setItems(List<OrderItemResponse> items) {
		this.items = items;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
