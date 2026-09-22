package com.tgd.dto.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.tgd.dto.response.payment.OrderItemResponse;
import com.tgd.dto.response.payment.OrderResponse;
import com.tgd.entity.payment.Order;

public class OrderMapperDTO {

	public static OrderResponse toOrderResponse(Order order) {
		OrderResponse response = new OrderResponse();
		response.setId(order.getId());
		response.setOrderCode(order.getOrderCode());
		response.setSubtotalAmount(order.getSubtotalAmount());
		response.setShippingFee(order.getShippingFee());
		response.setTotalAmount(order.getTotalAmount());
		response.setOrderStatus(order.getOrderStatus());
		response.setPaymentStatus(order.getPaymentStatus());
		response.setPaymentMethod(order.getPaymentMethod());
		response.setShippingFullName(order.getShippingFullName());
		response.setShippingPhoneNumber(order.getShippingPhoneNumber());
		response.setShippingAddress(order.getShippingAddress());
		response.setCreatedAt(order.getCreatedAt());

		if (order.getItems() != null && !order.getItems().isEmpty()) {
			List<OrderItemResponse> itemResponses = order.getItems().stream().map(item -> {
				OrderItemResponse itemResp = new OrderItemResponse();
				itemResp.setId(item.getId());
				itemResp.setProductId(item.getProductId());
				itemResp.setProductName(item.getProductName());
				itemResp.setUnitPrice(item.getUnitPrice());
				itemResp.setQuantity(item.getQuantity());
				itemResp.setSubtotal(item.getSubtotal());
				return itemResp;
			}).collect(Collectors.toList());

			response.setItems(itemResponses);
		}

		return response;
	}

}
