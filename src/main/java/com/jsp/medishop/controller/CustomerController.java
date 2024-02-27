package com.jsp.medishop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.medishop.dto.Customer;
import com.jsp.medishop.response.ResponseStructure;
import com.jsp.medishop.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping(value = "/saveCustomer")
	public ResponseStructure<Customer> saveCustomerController(@RequestBody Customer customer) {
		return customerService.saveCustomerService(customer);
	}

	@GetMapping(value = "/getCustomerById/{customerId}")
	public ResponseStructure<Customer> getCustomerByIdController(@PathVariable int customerId) {
		return customerService.getCustomerByIdService(customerId);
	}

	@GetMapping(value = "/getAllCustomer")
	public ResponseStructure<List<Customer>> getAllCustomerCustomer() {
		return customerService.getAllCustomerService();
	}

	@GetMapping(value = "/getCustomerByEmail/{customerEmail}")
	public ResponseStructure<Customer> getCustomerByEmailController(@PathVariable String customerEmail) {
		return customerService.getCustomerByEmailService(customerEmail);
	}

	@DeleteMapping(value = "/deleteCustomerById/{customerId}")
	public ResponseStructure<Customer> deleteCustomerByIdController(@PathVariable int customerId) {
		return customerService.deleteCustomerByIdService(customerId);
	}
	
	@GetMapping(value = "/loginCustomerByEmailAndPassword/{customerEmail}/{customerPassword}")
	public ResponseStructure<Customer> loginCustomerByEmailAndPasswordController(@PathVariable String customerEmail,
			@PathVariable String customerPassword) {
		return customerService.loginCustomerByEmailAndPasswordService(customerEmail, customerPassword);
	}
	
	@GetMapping(value = "/logoutCustomer")
	public ResponseEntity<String> logoutCustomerController() {
		return customerService.logoutCustomer();
	}
}
