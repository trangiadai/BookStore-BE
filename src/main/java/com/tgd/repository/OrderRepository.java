package com.tgd.repository;

import com.tgd.dao.mappers.OrderMapper;
import com.tgd.dto.request.payment.AdminOrderSearchRequest;
import com.tgd.entity.payment.Order;
import com.tgd.entity.payment.OrderItem;
import com.tgd.enums.OrderStatus;
import com.tgd.enums.PaymentStatus;
import com.tgd.enums.SortDirection;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderRepository {
	private final OrderMapper orderMapper;

	public Long createOrder(Order order) {
		Map<String, Object> params = new HashMap<>();
		params.put("accountId", order.getAccountId());
		params.put("orderCode", order.getOrderCode());
		params.put("subtotalAmount", order.getSubtotalAmount());
		params.put("shippingFee", order.getShippingFee());
		params.put("totalAmount", order.getTotalAmount());
		params.put("orderStatus", order.getOrderStatus());
		params.put("paymentStatus", order.getPaymentStatus());
		params.put("paymentMethod", order.getPaymentMethod());
		params.put("shippingFullName", order.getShippingFullName());
		params.put("shippingPhoneNumber", order.getShippingPhoneNumber());
		params.put("shippingAddress", order.getShippingAddress());

		orderMapper.createOrder(params);

		if (params.get("id") == null) {
			throw new IllegalStateException("Failed to retrieve auto-generated order ID from database.");
		}

		return ((Number) params.get("id")).longValue();
	}

	public int createOrderItems(List<OrderItem> items) {
		Map<String, Object> params = new HashMap<>();
		params.put("items", items);

		return orderMapper.createOrderItems(params);
	}

	public List<Order> getOrdersByAccountId(Long accountId) {
		Map<String, Object> params = new HashMap<>();
		params.put("accountId", accountId);
		return orderMapper.getOrdersByAccountId(params);
	}

	public Order getOrderByIdAndAccountId(Long orderId, Long accountId) {
		Map<String, Object> params = new HashMap<>();
		params.put("orderId", orderId);
		params.put("accountId", accountId);
		return orderMapper.getOrderByIdAndAccountId(params);
	}

	public int updateOrderStatus(Long orderId, OrderStatus orderStatus, PaymentStatus paymentStatus) {
		Map<String, Object> params = new HashMap<>();
		params.put("orderId", orderId);
		params.put("orderStatus", orderStatus);
		params.put("paymentStatus", paymentStatus);

		return orderMapper.updateOrderStatus(params);
	}
	
	public List<Order> searchOrdersByAdmin(AdminOrderSearchRequest request) {
	    Map<String, Object> params = new HashMap<>();
	    params.put("keyword", request.getKeyword());
	    params.put("orderStatus", request.getOrderStatus() != null ? request.getOrderStatus().name() : null);
	    params.put("paymentStatus", request.getPaymentStatus() != null ? request.getPaymentStatus().name() : null);
	    params.put("sortDirection", request.getSortDirection() != null ? request.getSortDirection() : SortDirection.DESC);

	    return orderMapper.searchOrdersByAdmin(params);
	}

	public OrderRepository(OrderMapper orderMapper) {
		super();
		this.orderMapper = orderMapper;
	}

}