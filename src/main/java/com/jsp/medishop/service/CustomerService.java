package com.jsp.medishop.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jsp.medishop.dto.Customer;
import com.jsp.medishop.response.ResponseStructure;

public interface CustomerService {

	public ResponseStructure<Customer> saveCustomerService(Customer customer);
	public ResponseStructure<Customer> getCustomerByIdService(int customerId);
	public ResponseStructure<List<Customer>> getAllCustomerService();
	public ResponseStructure<Customer> getCustomerByEmailService(String customerEmail);
	public ResponseStructure<Customer> updateCustomerByEmailService(Customer customer);
	public ResponseStructure<Customer> deleteCustomerByIdService(int customerId);
	
	public ResponseStructure<Customer> loginCustomerByEmailAndPasswordService(String customerEmail, String customerPassword);
	public ResponseEntity<String> logoutCustomer();
}
