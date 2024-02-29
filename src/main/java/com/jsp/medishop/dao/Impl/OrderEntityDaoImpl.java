package com.jsp.medishop.dao.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.medishop.dao.OrderEntityDao;
import com.jsp.medishop.dto.OrderEntity;
import com.jsp.medishop.repository.OrderEntityRepository;

@Repository
public class OrderEntityDaoImpl implements OrderEntityDao {
	
	@Autowired
	private OrderEntityRepository entityRepository;

	@Override
	public OrderEntity saveOrderEntityDao(OrderEntity entity) {
		return entityRepository.save(entity);
	}

	@Override
	public OrderEntity getOrderEntityByIdDao(int orderId) {
		Optional<OrderEntity> optional = entityRepository.findById(orderId);
		return optional.isPresent() ? optional.get() : null;
	}

}
