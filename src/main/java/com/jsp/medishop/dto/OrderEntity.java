package com.jsp.medishop.dto;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderEntity {

	@Id
	private int orderId;
	private LocalDate estimatedDdeliveryDate;
	private LocalDate customerDeliveryDate;
	private LocalDate orderDate;
	private String orderStatus="pending";
	private double totalAmount;
	private String deliveryAddress;
	private String paymentMode;
	private int orderQuantity;
	
	@OneToOne
	private Vendor vendor;
	
	@OneToOne
	private Medicine medicine;
	
	@OneToOne
	private Customer customer;
}
