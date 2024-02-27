package com.jsp.medishop.dao.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.medishop.dao.VendorDao;
import com.jsp.medishop.dto.Vendor;
import com.jsp.medishop.repository.VendorRepository;
@Repository
public class VendorDaoImpl implements VendorDao{

	@Autowired
	private VendorRepository vendorRepository;
	
	@Override
	public Vendor saveVendorDao(Vendor vendor) {
		return vendorRepository.save(vendor);
	}

	@Override
	public Vendor getVendorById(int vendorId) {
		Optional<Vendor> optional = vendorRepository.findById(vendorId);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			return null;
		}
	}

	@Override
	public Vendor getVendorByEmailDao(String vendorEmail) {
		return vendorRepository.getByVendorEmail(vendorEmail);
	}

	@Override
	public List<Vendor> getAllVendorDao() {
		return vendorRepository.findAll();
	}

	@Override
	public Vendor updateVendorByEmailDao(Vendor vendor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteVendorByEmailDao(String vendorEmail) {
//		return vendorRepository.deleteByVendorEmail(vendorEmail);
		Vendor vendor = getVendorByEmailDao(vendorEmail);
		if(vendor != null) {
			vendorRepository.delete(vendor);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Vendor vendorVerifyByIdDao(int id) {
		Vendor vendor = getVendorById(id);
		if(vendor != null) {
			vendor.setVendorStatus("active");
			return vendorRepository.save(vendor);
		}else {			
			return null;
		}
	}

}
