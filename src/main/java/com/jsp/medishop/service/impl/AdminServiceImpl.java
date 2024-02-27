package com.jsp.medishop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.medishop.dao.AdminDao;
import com.jsp.medishop.dto.Admin;
import com.jsp.medishop.response.ResponseStructure;
import com.jsp.medishop.service.AdminService;

import jakarta.servlet.http.HttpSession;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private HttpSession httpSession;

	@Autowired
	private AdminDao adminDao;

	@Autowired
	private ResponseStructure<Admin> responseStructure;

	@Override
	public ResponseStructure<Admin> loginAdminByEmailAndPassword(Admin admin) {
		Admin admin1 = adminDao.loginAdminByEmailAndPassword(admin);
		if (admin1 != null) {
			if (admin1.getAdminPassword().equals(admin.getAdminPassword())) {
				httpSession.setAttribute("adminEmail", admin1.getAdminEmail());
				responseStructure.setStatusCode(HttpStatus.OK.value());
				responseStructure.setStatusMessage("Admin login successful");
				admin.setAdminPassword("*******");
				responseStructure.setStatusData(admin1);
			}else {
				responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
				responseStructure.setStatusMessage("invalid-password");
				responseStructure.setStatusData(admin);				
			}
		} else {
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure.setStatusMessage("invalid-email");
			responseStructure.setStatusData(admin);
		}
		return responseStructure;
	}
	
	@Override
	public ResponseEntity<String> logoutAdmin() {
		
		if(httpSession.getAttribute("adminEmail") != null) {
			httpSession.invalidate();
			return new ResponseEntity<String>("logout--successfully",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("login first",HttpStatus.OK);
		}
	}

}
