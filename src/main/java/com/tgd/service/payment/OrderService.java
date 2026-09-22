package com.tgd.service.payment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tgd.dto.mappers.OrderMapperDTO;
import com.tgd.dto.request.payment.AdminOrderSearchRequest;
import com.tgd.dto.request.payment.CheckoutRequest;
import com.tgd.dto.request.payment.UpdateOrderStatusRequest;
import com.tgd.dto.response.ProductResponse;
import com.tgd.dto.response.payment.OrderResponse;
import com.tgd.entity.payment.Cart;
import com.tgd.entity.payment.CartItem;
import com.tgd.entity.payment.Order;
import com.tgd.entity.payment.OrderItem;
import com.tgd.enums.OrderStatus;
import com.tgd.enums.PaymentStatus;
import com.tgd.enums.ShippingFee;
import com.tgd.enums.SortDirection;
import com.tgd.repository.OrderRepository;
import com.tgd.service.ProductService;

@Service
public class OrderService {
	private final OrderRepository orderRepository;
	private final CartService cartService;
	private final ProductService productService;

	@Transactional
	public OrderResponse checkout(Jwt jwt, CheckoutRequest request) {
		Long accountId = Long.parseLong(jwt.getSubject());
		Cart cart = cartService.getCartByAccountId(accountId);
		if (cart == null || cart.getItems() == null || cart.getItems().isEmpty()) {
			throw new IllegalArgumentException("Cart is empty. Cannot process checkout.");
		}

		BigDecimal subtotalAmount = BigDecimal.ZERO;
		List<OrderItem> orderItems = new ArrayList<>();

		for (CartItem cartItem : cart.getItems()) {
			ProductResponse productResponse = productService.getProductById(cartItem.getProductId());
			if (productResponse == null) {
				throw new IllegalArgumentException("Product not found with ID: " + cartItem.getProductId());
			}

			if (productResponse.getQuantity() < cartItem.getQuantity()) {
				throw new IllegalArgumentException("Insufficient stock for product: " + productResponse.getName());
			}

			BigDecimal itemSubtotal = productResponse.getSellingPrice()
					.multiply(BigDecimal.valueOf(cartItem.getQuantity()));
			subtotalAmount = subtotalAmount.add(itemSubtotal);

			OrderItem orderItem = new OrderItem();
			orderItem.setProductId(productResponse.getId());
			orderItem.setProductName(productResponse.getName());
			orderItem.setUnitPrice(productResponse.getSellingPrice());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setSubtotal(itemSubtotal);
			orderItems.add(orderItem);
			productService.decreaseStock(productResponse.getId(), cartItem.getQuantity());
		}

		BigDecimal shippingFee = ShippingFee.STADARD_FEE.getFee();
		BigDecimal totalAmount = subtotalAmount.add(shippingFee);

		Order order = new Order();
		order.setAccountId(accountId);
		order.setOrderCode("ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
		order.setSubtotalAmount(subtotalAmount);
		order.setShippingFee(shippingFee);
		order.setTotalAmount(totalAmount);
		order.setOrderStatus(OrderStatus.PROCESSING);
		order.setPaymentStatus(PaymentStatus.UNPAID);
		order.setPaymentMethod(request.getPaymentMethod());
		order.setShippingFullName(request.getShippingFullName());
		order.setShippingPhoneNumber(request.getShippingPhoneNumber());
		order.setShippingAddress(request.getShippingAddress());

		Long orderId = orderRepository.createOrder(order);
		order.setId(orderId);
		for (OrderItem item : orderItems) {
			item.setOrderId(orderId);
		}

		orderRepository.createOrderItems(orderItems);
		cartService.clearCartItems(cart.getId());
		order.setItems(orderItems);

		return OrderMapperDTO.toOrderResponse(order);
	}

	public List<OrderResponse> getUserOrders(Jwt jwt) {
		Long accountId = Long.parseLong(jwt.getSubject());
		List<Order> orders = orderRepository.getOrdersByAccountId(accountId);

		return orders.stream().map(OrderMapperDTO::toOrderResponse).collect(Collectors.toList());
	}

	public OrderResponse getOrderDetail(Jwt jwt, Long orderId) {
		Long accountId = Long.parseLong(jwt.getSubject());
		Order order = orderRepository.getOrderByIdAndAccountId(orderId, accountId);

		if (order == null) {
			throw new IllegalArgumentException("Order not found or access denied for ID: " + orderId);
		}

		return OrderMapperDTO.toOrderResponse(order);
	}

	public OrderResponse getOrderDetailByAdmin(Long orderId) {
		Order order = orderRepository.getOrderByIdAndAccountId(orderId, null);

		if (order == null) {
			throw new IllegalArgumentException("Order not found with ID: " + orderId);
		}

		return OrderMapperDTO.toOrderResponse(order);
	}

	@Transactional
	public OrderResponse updateOrderStatus(Long orderId, UpdateOrderStatusRequest request) {
		// Fetch existing order details (using accountId = null or dedicated query if
		// needed)
		Order order = orderRepository.getOrderByIdAndAccountId(orderId, null); // ensure getOrderById query handles null
																				// accountId or create
																				// getOrderById(orderId)

		if (order == null) {
			throw new IllegalArgumentException("Order not found with ID: " + orderId);
		}

		OrderStatus newOrderStatus = request.getOrderStatus() != null ? request.getOrderStatus()
				: order.getOrderStatus();
		PaymentStatus newPaymentStatus = request.getPaymentStatus() != null ? request.getPaymentStatus()
				: order.getPaymentStatus();

		orderRepository.updateOrderStatus(orderId, newOrderStatus, newPaymentStatus);
		order.setOrderStatus(newOrderStatus);
		order.setPaymentStatus(newPaymentStatus);

		return OrderMapperDTO.toOrderResponse(order);
	}

	public List<OrderResponse> searchOrdersByAdmin(String keyword, OrderStatus orderStatus, PaymentStatus paymentStatus,
			SortDirection sortDirection) {
		AdminOrderSearchRequest request = new AdminOrderSearchRequest();
		request.setKeyword(keyword);
		request.setOrderStatus(orderStatus);
		request.setPaymentStatus(paymentStatus);
		request.setSortDirection(sortDirection);
		List<Order> orders = orderRepository.searchOrdersByAdmin(request);

		return orders.stream().map(OrderMapperDTO::toOrderResponse).collect(Collectors.toList());
	}

	public OrderService(OrderRepository orderRepository, CartService cartService, ProductService productService) {
		super();
		this.orderRepository = orderRepository;
		this.cartService = cartService;
		this.productService = productService;
	}
}