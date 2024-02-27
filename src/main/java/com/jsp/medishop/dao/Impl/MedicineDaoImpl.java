package com.jsp.medishop.dao.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.jsp.medishop.dao.MedicineDao;
import com.jsp.medishop.dto.Medicine;
import com.jsp.medishop.repository.MedicineRepository;

@Repository
public class MedicineDaoImpl implements MedicineDao{
	
	@Autowired
	private MedicineRepository medicineRepository;

	@Override
	public Medicine saveMedicineDao(Medicine medicine) {
		return medicineRepository.save(medicine);
	}

	@Override
	public List<Medicine> getAllMedicineDao() {
		return medicineRepository.findAll();
	}

	@Override
	public Medicine getMedicineByIdDao(int medicineId) {
		Optional<Medicine> optional = medicineRepository.findById(medicineId);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			return null;
		}
	}

	@Override
	public Medicine verifyMedicineByAdminDao(int medicineId) {
		Medicine medicine = getMedicineByIdDao(medicineId);
		if(medicine != null) {
			medicine.setMedicineStatus("active");
			return medicineRepository.save(medicine);
		}else {
			return null;
		}
	}

}
