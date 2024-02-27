package com.jsp.medishop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.medishop.dto.Vendor;
import com.jsp.medishop.response.ResponseStructure;
import com.jsp.medishop.service.VendorService;

@RestController
public class VendorController {

	@Autowired
	private VendorService vendorService;

	@PostMapping(value = "/saveVendor")
	public ResponseStructure<Vendor> saveVendorController(@RequestBody Vendor vendor) {
		return vendorService.saveVendorService(vendor);
	}

	@GetMapping(value = "/getVendorById/{vendorId}")
	public ResponseStructure<Vendor> getVendorByIdController(@PathVariable int vendorId) {
		return vendorService.getVendorByIdService(vendorId);
	}

	@GetMapping(value = "/getVendorByEmail/{vendorEmail}")
	public ResponseStructure<Vendor> getVendorByEmailController(@PathVariable String vendorEmail) {
		return vendorService.getVendorByEmailService(vendorEmail);
	}
	
	@GetMapping(value = "/getAllVendor")
	public ResponseStructure<List<Vendor>> getAllVendorController() {
		return vendorService.getAllVendorService();
	}
	
	@DeleteMapping(value = "/deleteVendorByEmail/{vendorEmail}")
	public ResponseStructure<Vendor> deleteVendorByEmailService(@PathVariable String vendorEmail) {
		return vendorService.deleteVendorByEmailService(vendorEmail);
	}
	
	@GetMapping(value = "/loginVendorByEmailAndPassword/{vendorEmail}/{vendorPassword}")
	public ResponseStructure<Vendor> loginVendorByEmailAndPasswordService(@PathVariable String vendorEmail, @PathVariable String vendorPassword){
		return vendorService.loginVendorByEmailAndPasswordService(vendorEmail, vendorPassword);
	}
	
	@GetMapping(value = "/logoutVendor")
	public ResponseEntity<String> logoutVendorController() {
		return vendorService.logoutVendor();
	}
}
