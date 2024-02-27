package com.jsp.medishop.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jsp.medishop.dto.Vendor;
import com.jsp.medishop.response.ResponseStructure;

public interface VendorService {

	public ResponseStructure<Vendor> saveVendorService(Vendor vendor);
	public ResponseStructure<Vendor> getVendorByIdService(int vendorId);
	public ResponseStructure<Vendor> getVendorByEmailService(String vendorEmail);
	public ResponseStructure<List<Vendor>> getAllVendorService();
	public ResponseStructure<Vendor> updateVendorByEmailService(Vendor vendor);
	public ResponseStructure<Vendor> deleteVendorByEmailService(String vendorEmail);
	
	public ResponseStructure<Vendor> loginVendorByEmailAndPasswordService(String vendorEmail, String vendorPassword);
	public ResponseEntity<String> logoutVendor();
	
	public ResponseEntity<String> vendorVerifyByIdService(int id);
}