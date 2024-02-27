package com.jsp.medishop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.medishop.dto.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Integer>{

	public Vendor getByVendorEmail(String vendorEmail);
	
//	public Vendor deleteByVendorEmail(String vendorEmail);
}
