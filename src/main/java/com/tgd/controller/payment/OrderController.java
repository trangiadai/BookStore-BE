package com.tgd.controller.payment;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgd.dto.request.payment.CheckoutRequest;
import com.tgd.dto.response.payment.OrderResponse;
import com.tgd.service.payment.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrderController {
	private final OrderService orderService;

	@PostMapping("/checkout")
	public OrderResponse checkout(@AuthenticationPrincipal Jwt jwt, @Valid @RequestBody CheckoutRequest request) {
		return orderService.checkout(jwt, request);
	}
	
	@GetMapping
	public List<OrderResponse> getUserOrders(@AuthenticationPrincipal Jwt jwt) {
	    return orderService.getUserOrders(jwt);
	}

	@GetMapping("/{id}")
	public OrderResponse getOrderDetail(@AuthenticationPrincipal Jwt jwt, @PathVariable("id") Long orderId) {
		return orderService.getOrderDetail(jwt, orderId);
	}

	public OrderController(OrderService orderService) {
		super();
		this.orderService = orderService;
	}
}