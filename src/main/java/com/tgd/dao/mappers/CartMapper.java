package com.tgd.dao.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.tgd.entity.payment.Cart;

import java.util.Map;

@Mapper
public interface CartMapper {
	Cart getCartByAccountId(Map<String, Object> params);

	int createCart(Map<String, Object> params);

	int upsertCartItem(Map<String, Object> params);

	int updateCartItemQuantity(Map<String, Object> params);

	int removeCartItem(Map<String, Object> params);

	int clearCartItems(Map<String, Object> params);
}