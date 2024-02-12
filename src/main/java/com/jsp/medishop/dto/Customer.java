package com.jsp.medishop.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Customer {

	@Id
	private int customerId;
	private String customerName;
	private LocalDate customerAge;
	private String customerEmail;
	private String customerPassword;
	private String customerAddress;
	private long customerPhone;
	private long customerAdharNumber;
	
	@ManyToMany(mappedBy = "customers")
	private List<Vendor> vendors;
}
