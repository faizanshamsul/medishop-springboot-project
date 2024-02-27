package com.jsp.medishop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.medishop.dto.Admin;
import com.jsp.medishop.response.ResponseStructure;
import com.jsp.medishop.service.AdminService;
import com.jsp.medishop.service.MedicineService;
import com.jsp.medishop.service.VendorService;

@RestController
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private VendorService vendorService;
	
	@Autowired
	private MedicineService medicineService;
	
	@GetMapping(value = "/loginAdminByEmailAndPassword")
	public ResponseStructure<Admin> loginAdminByEmailAndPasswordController(@RequestBody Admin admin) {
		return adminService.loginAdminByEmailAndPassword(admin);
	}
	
	@RequestMapping(value = "/logoutAdmin")
	public ResponseEntity<String> logoutAdminController() {
		return adminService.logoutAdmin();
	}
	
	@PostMapping(value = "/vendorVerifyById/{id}")
	public ResponseEntity<String> vendorVerifyByIdController(@PathVariable int id) {
		return vendorService.vendorVerifyByIdService(id);
	}
	
	@PostMapping(value = "/verifyMedicineByAdmin/{medicineId}")
	public ResponseEntity<String> verifyMedicineByAdminController(@PathVariable int medicineId) {
		return medicineService.verifyMedicineByAdminService(medicineId);
	}
}
