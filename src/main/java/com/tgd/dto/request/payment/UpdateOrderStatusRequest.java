package com.tgd.dto.request.payment;

import com.tgd.enums.OrderStatus;
import com.tgd.enums.PaymentStatus;

public class UpdateOrderStatusRequest {
	private OrderStatus orderStatus;
	private PaymentStatus paymentStatus;

	public UpdateOrderStatusRequest(OrderStatus orderStatus, PaymentStatus paymentStatus) {
		super();
		this.orderStatus = orderStatus;
		this.paymentStatus = paymentStatus;
	}

	public UpdateOrderStatusRequest() {
		super();
		// TODO Auto-generated constructor stub
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
}
