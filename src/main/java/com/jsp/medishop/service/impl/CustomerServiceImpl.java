package com.jsp.medishop.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.medishop.dao.CustomerDao;
import com.jsp.medishop.dao.Impl.CustomerDaoImpl;
import com.jsp.medishop.dto.Customer;
import com.jsp.medishop.repository.CustomerRepository;
import com.jsp.medishop.response.ResponseStructure;
import com.jsp.medishop.service.CustomerService;
import com.jsp.medishop.verification.EmailPasswordVerification;

import jakarta.servlet.http.HttpSession;

/**
 * Faizan Shams
 */

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerDaoImpl customerDaoImpl;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private ResponseStructure<Customer> responseStructure;
	
	@Autowired
	private ResponseStructure<List<Customer>> responseStructure2;
	
	@Autowired
	private EmailPasswordVerification verification;
	
	@Autowired
	private HttpSession httpSession;
	
	@Override
	public ResponseStructure<Customer> saveCustomerService(Customer customer) {
		String email = verification.verifyEmail(customer.getCustomerEmail());
		String password = verification.verifyPassword(customer.getCustomerPassword());
		
		if(email != null) {
			if(password != null) {
				int currentYear = LocalDate.now().getYear();
				int customerBirthYear = customer.getCustomerAge().getYear();
				
				int age = currentYear-customerBirthYear;
				
				if(age>=18) {
					Customer customer1 = customerDaoImpl.saveCustomerDao(customer);
					responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
					responseStructure.setStatusMessage("Data-stored");
					responseStructure.setStatusData(customer1);					
				}else {
					responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
					responseStructure.setStatusMessage("yor are under 18 year");
					responseStructure.setStatusData(null);	
				}
				
				
			}else {
				responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
				responseStructure.setStatusMessage("error-check ur password");
				responseStructure.setStatusData(null);
			}
		}else {
			responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			responseStructure.setStatusMessage("error-check ur email");
			responseStructure.setStatusData(null);
		}
		return responseStructure;
	}

	@Override
	public ResponseStructure<Customer> getCustomerByIdService(int customerId) {
		Customer customer = customerDaoImpl.getCustomerByIdDao(customerId);
		if(customer != null) {
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setStatusMessage("Id has found");
			responseStructure.setStatusData(customer);
		}else {
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure.setStatusMessage("Id has not found");
			responseStructure.setStatusData(null);
		}
		return responseStructure;
	}

	@Override
	public ResponseStructure<List<Customer>> getAllCustomerService() {
		List<Customer> customers = customerDaoImpl.getAllCustomerDao();
		if(customers != null) {
			responseStructure2.setStatusCode(HttpStatus.FOUND.value());
			responseStructure2.setStatusMessage("--all-data-found--");
			responseStructure2.setStatusData(customers);
		}else {
			responseStructure2.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure2.setStatusMessage("error-while-finding-data");
			responseStructure2.setStatusData(customers);
		}
		return responseStructure2;
	}

	@Override
	public ResponseStructure<Customer> getCustomerByEmailService(String customerEmail) {
		Customer customer = customerDao.getCustomerByEmailDao(customerEmail);
		if(customer != null) {
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setStatusMessage("Email-has-found");
			responseStructure.setStatusData(customer);
		}else {
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure.setStatusMessage("Email-has-not-found");
			responseStructure.setStatusData(customer);
		}
		return responseStructure;
	}

	@Override
	public ResponseStructure<Customer> updateCustomerByEmailService(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseStructure<Customer> deleteCustomerByIdService(int customerId) {
		customerDao.deleteCustomerByIdDao(customerId);
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setStatusMessage("data-has-been-deleted");
//		responseStructure.setStatusData();
		return responseStructure;
	}

	@Override
	public ResponseStructure<Customer> loginCustomerByEmailAndPasswordService(String customerEmail,
			String customerPassword) {
		Customer customer = customerDao.getCustomerByEmailDao(customerEmail);
		if(customer != null) {
			if(customer.getCustomerPassword().equals(customerPassword)) {
				httpSession.setAttribute("customerEmail", customerEmail);
				responseStructure.setStatusCode(HttpStatus.OK.value());
				responseStructure.setStatusMessage("Customer-login-successfull");
				customer.setCustomerPassword("*****");
				responseStructure.setStatusData(customer);
			}else {
				responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
				responseStructure.setStatusMessage("**Error**Customer-password-is-wrong");
				responseStructure.setStatusData(null);
			}
		}else {
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure.setStatusMessage("**Error**Customer-email-is-wrong");
			responseStructure.setStatusData(customer);
		}
		return responseStructure;
	}

	@Override
	public ResponseEntity<String> logoutCustomer() {
		if(httpSession.getAttribute("customerEmail") != null) {
			httpSession.invalidate();
			return new ResponseEntity<String>("Customer-logout-successfully", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Do login First", HttpStatus.NOT_ACCEPTABLE);
		}
	}

}
