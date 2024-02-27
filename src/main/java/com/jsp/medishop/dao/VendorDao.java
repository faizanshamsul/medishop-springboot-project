package com.jsp.medishop.dao;

import java.util.List;

import com.jsp.medishop.dto.Vendor;

public interface VendorDao {

	public Vendor saveVendorDao(Vendor vendor);
	public Vendor getVendorById(int vendorId);
	public Vendor getVendorByEmailDao(String vendorEmail);
	public List<Vendor> getAllVendorDao();
	public Vendor updateVendorByEmailDao(Vendor vendor);
	public boolean deleteVendorByEmailDao(String vendorEmail);
	public Vendor vendorVerifyByIdDao(int id);
}
