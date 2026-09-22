package com.tgd.dao.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.tgd.entity.payment.Order;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {
	int createOrder(Map<String, Object> params);

	int createOrderItems(Map<String, Object> params);

	List<Order> getOrdersByAccountId(Map<String, Object> params);

	Order getOrderByIdAndAccountId(Map<String, Object> params);

	int updateOrderStatus(Map<String, Object> params);

	List<Order> searchOrdersByAdmin(Map<String, Object> params);
}