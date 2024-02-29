package com.jsp.medishop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.jsp.medishop.dao.CustomerDao;
import com.jsp.medishop.dao.OrderEntityDao;
import com.jsp.medishop.dto.OrderEntity;
import com.jsp.medishop.response.ResponseStructure;
import com.jsp.medishop.service.OrderEntityService;

import jakarta.servlet.http.HttpSession;

@Service
public class OrderEntityServiceImpl implements OrderEntityService{
	
	@Autowired
	private OrderEntityDao entityDao;
	
	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private ResponseStructure<OrderEntity> responseStructure;
	
	@Autowired
	private CustomerDao customerDao;

	@Override
	public ResponseStructure<OrderEntity> saveOrderEntityService(OrderEntity entity) {
		String customerEmail = (String)httpSession.getAttribute("customerEmail");
		if(customerEmail != null) {
			
			entityDao.saveOrderEntityDao(entity);
			responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
			responseStructure.setStatusMessage("data-saved");
			responseStructure.setStatusData(entity);			
		}else {
			responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			responseStructure.setStatusMessage("data-not-saved");
			responseStructure.setStatusData(entity);
		}
		return null;
	}

	@Override
	public ResponseStructure<OrderEntity> getOrderEntityByIdService(int orderId) {
		// TODO Auto-generated method stub
		return null;
	}

}
