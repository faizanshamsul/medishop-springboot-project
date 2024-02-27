package com.jsp.medishop.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Vendor {

	@Id
	private int vendorId;
	private String vendorName;
	@Column(unique = true, nullable = false)
	private String vendorEmail;
//	@Column(length = 16)
	private String vendorPassword;
	private String vendorAddress;
	@Column(unique = true, nullable = false)
	private long vendorPhone;
	@Column(unique = true, nullable = false, length = 12)
	private long vendorAdharNumber;
	private String vendorStatus="inactive";
	
	@ManyToMany
	private List<Customer> customers;
	
	@ManyToMany(mappedBy = "vendors")
	private List<Medicine> medicines;
}
