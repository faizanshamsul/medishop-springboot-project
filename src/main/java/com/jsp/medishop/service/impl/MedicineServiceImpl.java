package com.jsp.medishop.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.medishop.dao.CustomerDao;
import com.jsp.medishop.dao.MedicineDao;
import com.jsp.medishop.dao.VendorDao;
import com.jsp.medishop.dto.Medicine;
import com.jsp.medishop.dto.Vendor;
import com.jsp.medishop.response.ResponseStructure;
import com.jsp.medishop.service.MedicineService;

import jakarta.servlet.http.HttpSession;

@Service
public class MedicineServiceImpl implements MedicineService{
	
	@Autowired
	private MedicineDao medicineDao;
	
	@Autowired
	private ResponseStructure<Medicine> responseStructure;
	
	@Autowired
	private ResponseStructure<List<Medicine>> responseStructure2;

	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private VendorDao vendorDao;
	
	@Override
	public ResponseStructure<Medicine> saveMedicineService(Medicine medicine) {
		
		String email = (String)httpSession.getAttribute("vendorEmail");
		
		if(email != null) {
			Vendor vendor = vendorDao.getVendorByEmailDao(email);
			medicine.setVendors(new ArrayList<Vendor>(Arrays.asList(vendor)));
			Medicine medicine1 = medicineDao.saveMedicineDao(medicine);
			if(medicine1 != null) {
				responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
				responseStructure.setStatusMessage("---Data is stored---");
				responseStructure.setStatusData(medicine1);
			}else {
				responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
				responseStructure.setStatusMessage("---Error while storing data---");
				responseStructure.setStatusData(medicine1);
			}			
		}else {
			responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			responseStructure.setStatusMessage("---Do login vendor and then verify---");
			responseStructure.setStatusData(null);
		}
		return responseStructure;
	}

	@Override
	public ResponseStructure<List<Medicine>> getAllMedicineService() {
		List<Medicine> medicines = medicineDao.getAllMedicineDao();
		if(medicines != null) {
			responseStructure2.setStatusCode(HttpStatus.OK.value());
			responseStructure2.setStatusMessage("All-data-found");
			responseStructure2.setStatusData(medicines);
		}else {
			responseStructure2.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure2.setStatusMessage("No-data-found");
			responseStructure2.setStatusData(medicines);
		}
		return responseStructure2;
	}

	@Override
	public ResponseStructure<Medicine> getMedicineByIdService(int medicineId) {
		Medicine medicine = medicineDao.getMedicineByIdDao(medicineId);
		if(medicine != null) {
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setStatusMessage("Data-found");
			responseStructure.setStatusData(medicine);
		}else {
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure.setStatusMessage("No-data-found");
			responseStructure.setStatusData(medicine);
		}
		return responseStructure;
	}

	@Override
	public ResponseEntity<String> verifyMedicineByAdminService(int medicineId) {
		if(httpSession.getAttribute("adminEmail") != null) {
			medicineDao.verifyMedicineByAdminDao(medicineId);
			return new ResponseEntity<String>("medicine-verified-successfully", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Do login Admin then verify", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public ResponseStructure<List<Medicine>> getAllMedicineByNameDao(String medicineName) {
		// TODO Auto-generated method stub
		return null;
	}

}
