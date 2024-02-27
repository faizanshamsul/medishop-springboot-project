package com.jsp.medishop.dao.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.jsp.medishop.dao.CustomerDao;
import com.jsp.medishop.dto.Customer;
import com.jsp.medishop.repository.CustomerRepository;

@Repository
public class CustomerDaoImpl implements CustomerDao{
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer saveCustomerDao(Customer customer) {		
		return customerRepository.save(customer);
	}

	@Override
	public Customer getCustomerByIdDao(int customerId) {
		Optional<Customer> optional = customerRepository.findById(customerId);
		if(optional.isPresent()) {
			return optional.get();
		}else{			
			return null;
		}
	}

	@Override
	public List<Customer> getAllCustomerDao() {
		return customerRepository.findAll();
	}

	@Override
	public Customer getCustomerByEmailDao(String customerEmail) {
		return customerRepository.findByCustomerEmail(customerEmail);
	}

	@Override
	public Customer updateCustomerByEmailDao(Customer customer) {
		return null;
	}

	@Override
	public boolean deleteCustomerByIdDao(int customerId) {
		Customer customer = getCustomerByIdDao(customerId);
		if(customer != null) {
			customerRepository.delete(customer);
			return true;
		}else {
			return false;
		}
	}

}
