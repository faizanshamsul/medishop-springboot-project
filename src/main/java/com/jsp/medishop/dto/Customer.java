package com.jsp.medishop.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
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
	@Column(unique = true, nullable = false)
	private String customerEmail;
//	@Column(length = 16)
	private String customerPassword;
	private String customerAddress;
	@Column(unique = true, nullable = false)
	private long customerPhone;
	@Column(unique = true, nullable = false, length = 12)
	private long customerAdharNumber;
	
	@ManyToMany(mappedBy = "customers")
	private List<Vendor> vendors;
}
