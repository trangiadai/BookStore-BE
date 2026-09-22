package com.tgd.controller.admin;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import com.tgd.dto.request.payment.UpdateOrderStatusRequest;
import com.tgd.dto.response.payment.OrderResponse;
import com.tgd.enums.OrderStatus;
import com.tgd.enums.PaymentStatus;
import com.tgd.enums.SortDirection;
import com.tgd.service.payment.OrderService;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@RestController
@RequestMapping("/admin/orders")
public class AdminOrderController {
	private final OrderService orderService;

	@PatchMapping("/{id}/status")
	public ResponseEntity<OrderResponse> updateOrderStatus(@PathVariable("id") Long orderId,
			@RequestBody UpdateOrderStatusRequest request) {

		OrderResponse response = orderService.updateOrderStatus(orderId, request);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/search")
	public List<OrderResponse> searchOrders(
			@RequestParam(value = "keyword", required = false) @Size(max = 50, message = "Keyword length cannot exceed 50 characters") String keyword,
			@RequestParam(value = "orderStatus", required = false) OrderStatus orderStatus,
			@RequestParam(value = "paymentStatus", required = false) PaymentStatus paymentStatus,
			@RequestParam(value = "sortDirection", required = false, defaultValue = "DESC") SortDirection sortDirection) {

		return orderService.searchOrdersByAdmin(keyword, orderStatus, paymentStatus, sortDirection);
	}

	@GetMapping("/{id}")
	public OrderResponse getOrderDetailByAdmin(
			@PathVariable("id") @Positive(message = "Order ID must be greater than 0") Long orderId) {

		return orderService.getOrderDetailByAdmin(orderId);
	}

	public AdminOrderController(OrderService orderService) {
		super();
		this.orderService = orderService;
	}
}