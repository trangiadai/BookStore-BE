package com.tgd.dto.request.payment;

import com.tgd.enums.OrderStatus;
import com.tgd.enums.PaymentStatus;
import com.tgd.enums.SortDirection;

public class AdminOrderSearchRequest {
	private String keyword;
	private OrderStatus orderStatus;
	private PaymentStatus paymentStatus;
	private SortDirection sortDirection = SortDirection.DESC;

	public AdminOrderSearchRequest(String keyword, OrderStatus orderStatus, PaymentStatus paymentStatus,
			SortDirection sortDirection) {
		super();
		this.keyword = keyword;
		this.orderStatus = orderStatus;
		this.paymentStatus = paymentStatus;
		this.sortDirection = sortDirection;
	}

	public AdminOrderSearchRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
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

	public SortDirection getSortDirection() {
		return sortDirection;
	}

	public void setSortDirection(SortDirection sortDirection) {
		this.sortDirection = sortDirection;
	}

}
