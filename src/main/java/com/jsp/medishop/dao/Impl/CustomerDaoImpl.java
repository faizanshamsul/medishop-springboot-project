package com.jsp.medishop.dao.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jsp.medishop.dao.CustomerDao;
import com.jsp.medishop.dto.Customer;
import com.jsp.medishop.repository.CustomerRepository;

public class CustomerDaoImpl implements CustomerDao{
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer saveCustomerDao(Customer customer) {		
		return customerRepository.save(customer);
	}

	@Override
	public Customer getCustomerByIdDao(int customerId) {
		return null;
	}

	@Override
	public List<Customer> getAllCustomerDao() {
		return null;
	}

	@Override
	public Customer getCusyomerByEmailDao(String customerEmail) {
		return null;
	}

	@Override
	public Customer updateCustomerByEmailDao(Customer customer) {
		return null;
	}

	@Override
	public boolean deleteCustomerByIdDao(int customerId) {
		return false;
	}

}
