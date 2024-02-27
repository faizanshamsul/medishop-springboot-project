package com.jsp.medishop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.medishop.dao.VendorDao;
import com.jsp.medishop.dto.Vendor;
import com.jsp.medishop.response.ResponseStructure;
import com.jsp.medishop.service.VendorService;
import com.jsp.medishop.verification.EmailPasswordVerification;

import jakarta.servlet.http.HttpSession;

@Service
public class VendorServiceImpl implements VendorService {
	
	@Autowired
	private HttpSession httpSession;

	@Autowired
	private VendorDao vendorDao;

	@Autowired
	private ResponseStructure<Vendor> responseStructure;

	@Autowired
	private ResponseStructure<List<Vendor>> responseStructure2;

	@Autowired
	private EmailPasswordVerification verification;

	@Override
	public ResponseStructure<Vendor> saveVendorService(Vendor vendor) {
		String email = verification.verifyEmail(vendor.getVendorEmail());
		String password = verification.verifyPassword(vendor.getVendorPassword());

		if (email != null) {
			if (password != null) {
				Vendor vendor1 = vendorDao.saveVendorDao(vendor);
				responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
				responseStructure.setStatusMessage("---Data stored---");
				responseStructure.setStatusData(vendor1);
			} else {
				responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
				responseStructure.setStatusMessage("***error*** check your password");
				responseStructure.setStatusData(null);
			}
		} else {
			responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			responseStructure.setStatusMessage("***error*** check your email");
			responseStructure.setStatusData(null);
		}
		return responseStructure;
	}

	@Override
	public ResponseStructure<Vendor> getVendorByIdService(int vendorId) {
		Vendor vendor = vendorDao.getVendorById(vendorId);
		if (vendor != null) {
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setStatusMessage("Data-Found");
			responseStructure.setStatusData(vendor);
		} else {
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure.setStatusMessage("Data-not-Found");
			responseStructure.setStatusData(vendor);
		}
		return responseStructure;
	}

	@Override
	public ResponseStructure<Vendor> getVendorByEmailService(String vendorEmail) {
		Vendor vendor = vendorDao.getVendorByEmailDao(vendorEmail);
		if (vendor != null) {
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setStatusMessage("Data-Found");
			responseStructure.setStatusData(vendor);
		} else {
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure.setStatusMessage("Data-not-Found");
			responseStructure.setStatusData(vendor);
		}
		return responseStructure;
	}

	@Override
	public ResponseStructure<List<Vendor>> getAllVendorService() {
		List<Vendor> vendors = vendorDao.getAllVendorDao();
		if(vendors != null) {
			responseStructure2.setStatusCode(HttpStatus.FOUND.value());
			responseStructure2.setStatusMessage("--found--");
			responseStructure2.setStatusData(vendors);
		}else {
			responseStructure2.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure2.setStatusMessage("--found--");
			responseStructure2.setStatusData(vendors);
		}
		return responseStructure2;
	}

	@Override
	public ResponseStructure<Vendor> updateVendorByEmailService(Vendor vendor) {
		
		return null;
	}

	@Override
	public ResponseStructure<Vendor> deleteVendorByEmailService(String vendorEmail) {
		vendorDao.deleteVendorByEmailDao(vendorEmail);
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setStatusMessage("Data-Found");
//		responseStructure.setStatusData(vendor);
		return responseStructure;
	}
	
	@Override
	public ResponseStructure<Vendor> loginVendorByEmailAndPasswordService(String vendorEmail, String vendorPassword){
		Vendor vendor = vendorDao.getVendorByEmailDao(vendorEmail);
		if(vendor != null) {
			if((vendor.getVendorPassword().equals(vendorPassword)) && (vendor.getVendorStatus().equalsIgnoreCase("active"))) {
				httpSession.setAttribute("vendorEmail", vendorEmail);
				responseStructure.setStatusCode(HttpStatus.OK.value());
				responseStructure.setStatusMessage("Vendor-login-successfull");
				vendor.setVendorPassword("********");
				responseStructure.setStatusData(vendor);
			}else {
				responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
				responseStructure.setStatusMessage("vendor-password-incorrect...or vendor is not verified by admin");
				responseStructure.setStatusData(null);
			}
		}else {
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure.setStatusMessage("vendor-email-incorrect");
			responseStructure.setStatusData(vendor);
		}
		return responseStructure;
	}

	@Override
	public ResponseEntity<String> logoutVendor() {
		if(httpSession.getAttribute("vendorEmail") != null) {
			httpSession.invalidate();
			return new ResponseEntity<String>("Vendor-logout-successfull", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("login first", HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<String> vendorVerifyByIdService(int id) {
		if(httpSession.getAttribute("adminEmail") != null) {
			vendorDao.vendorVerifyByIdDao(id);
			return new ResponseEntity<String>("vendor verified successfully", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("do login admin and then verify", HttpStatus.NOT_ACCEPTABLE);
		}
	}
}
