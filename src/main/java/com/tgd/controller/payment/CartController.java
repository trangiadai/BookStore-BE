package com.tgd.controller.payment;

import com.tgd.dto.request.payment.AddToCartRequest;
import com.tgd.dto.request.payment.UpdateCartItemQuantityRequest;
import com.tgd.dto.response.payment.CartResponse;
import com.tgd.service.payment.CartService;

import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
	private final CartService cartService;

	@GetMapping
	public CartResponse getMyCart(@AuthenticationPrincipal Jwt jwt) {
		return cartService.getMyCart(jwt);
	}

	@PostMapping("/items")
	public CartResponse addToCart(@AuthenticationPrincipal Jwt jwt, @Valid @RequestBody AddToCartRequest request) {
		return cartService.addToCart(jwt, request);
	}

	@DeleteMapping("/items/{productId}")
	public CartResponse removeCartItem(@AuthenticationPrincipal Jwt jwt, @PathVariable("productId") Long productId) {
		return cartService.removeCartItem(jwt, productId);
	}

	@PatchMapping("/items/{productId}")
	public CartResponse updateItemQuantity(@AuthenticationPrincipal Jwt jwt, @PathVariable("productId") Long productId,
			@Valid @RequestBody UpdateCartItemQuantityRequest request) {
		return cartService.updateItemQuantity(jwt, productId, request.getQuantity());
	}

	public CartController(CartService cartService) {
		super();
		this.cartService = cartService;
	}
}