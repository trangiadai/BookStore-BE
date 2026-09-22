package com.tgd.repository;

import com.tgd.dao.mappers.CartMapper;
import com.tgd.entity.payment.Cart;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CartRepository {
	private final CartMapper cartMapper;

	public Cart getCartByAccountId(Long accountId) {
		Map<String, Object> params = new HashMap<>();
		params.put("accountId", accountId);
		
		return cartMapper.getCartByAccountId(params);
	}

	public Long createCart(Long accountId) {
		Map<String, Object> params = new HashMap<>();
		params.put("accountId", accountId);
		cartMapper.createCart(params);
		
		return ((Number) params.get("id")).longValue();
	}

	public int upsertCartItem(Long cartId, Long productId, Integer quantity) {
		Map<String, Object> params = new HashMap<>();
		params.put("cartId", cartId);
		params.put("productId", productId);
		params.put("quantity", quantity);
		
		return cartMapper.upsertCartItem(params);
	}
	
	public int removeCartItem(Long cartId, Long productId) {
		Map<String, Object> params = new HashMap<>();
		params.put("cartId", cartId);
		params.put("productId", productId);
		
		return cartMapper.removeCartItem(params);
	}

	public int updateCartItemQuantity(Long cartId, Long productId, Integer quantity) {
		Map<String, Object> params = new HashMap<>();
		params.put("cartId", cartId);
		params.put("productId", productId);
		params.put("quantity", quantity);
		
		return cartMapper.updateCartItemQuantity(params);
	}

	public int clearCartItems(Long cartId) {
		Map<String, Object> params = new HashMap<>();
		params.put("cartId", cartId);
		
		return cartMapper.clearCartItems(params);
	}

	public CartRepository(CartMapper cartMapper) {
		super();
		this.cartMapper = cartMapper;
	}

}