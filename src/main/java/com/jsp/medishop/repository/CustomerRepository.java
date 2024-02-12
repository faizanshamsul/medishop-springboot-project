package com.jsp.medishop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.medishop.dto.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
