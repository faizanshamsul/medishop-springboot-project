package com.jsp.medishop.dao.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.medishop.dao.AdminDao;
import com.jsp.medishop.dto.Admin;
import com.jsp.medishop.repository.AdminRepository;

@Repository
public class AdminDaoImpl implements AdminDao {
	
	@Autowired
	private AdminRepository adminRepository;

	@Override
	public Admin loginAdminByEmailAndPassword(Admin admin) {
		return adminRepository.findByAdminEmail(admin.getAdminEmail());
	}

}
