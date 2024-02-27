package com.jsp.medishop.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Medicine {

	@Id
	private int medicineId;
	private String medicineName;
//	@JsonFormat(pattern = "dd-mm-yyyy")
	@Column(name = "Expiry_Date")
	private LocalDate expiryDate;
	private double price;
	@Column(length = 240)
	private String desciption;
	@Column(name = "medicine_status")
	private String medicineStatus = "inactive";
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Vendor> vendors;
}
