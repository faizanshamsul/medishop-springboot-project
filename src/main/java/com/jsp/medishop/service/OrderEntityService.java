package com.jsp.medishop.service;

import com.jsp.medishop.dto.OrderEntity;
import com.jsp.medishop.response.ResponseStructure;

public interface OrderEntityService {
	
	public ResponseStructure<OrderEntity> saveOrderEntityService(OrderEntity entity);
	public ResponseStructure<OrderEntity> getOrderEntityByIdService(int orderId);
}
