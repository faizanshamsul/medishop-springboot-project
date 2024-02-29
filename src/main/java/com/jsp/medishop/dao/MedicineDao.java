package com.jsp.medishop.dao;

import java.util.List;

import com.jsp.medishop.dto.Medicine;

public interface MedicineDao {

	public Medicine saveMedicineDao(Medicine medicine);
	public Medicine getMedicineByIdDao(int medicineId);
	public List<Medicine> getAllMedicineDao();
	
	public Medicine verifyMedicineByAdminDao(int medicineId);
	public List<Medicine> getAllMedicineByNameDao(String medicineName);
}
