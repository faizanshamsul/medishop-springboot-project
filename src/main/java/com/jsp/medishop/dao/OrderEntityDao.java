package com.jsp.medishop.dao;

import com.jsp.medishop.dto.OrderEntity;

public interface OrderEntityDao {

	public OrderEntity saveOrderEntityDao(OrderEntity entity);
	public OrderEntity getOrderEntityByIdDao(int orderId);
}
