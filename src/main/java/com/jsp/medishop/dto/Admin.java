package com.jsp.medishop.dto;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Admin {

	@Id
	private int adminId;
	private String adminEmail;
	private String adminPassword;
	
	@OneToMany
	private List<Vendor> vendors;

}
