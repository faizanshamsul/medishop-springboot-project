package com.jsp.medishop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.medishop.dto.Medicine;
import com.jsp.medishop.response.ResponseStructure;
import com.jsp.medishop.service.MedicineService;

@RestController
public class MedicineController {

	@Autowired
	private MedicineService medicineService;

	@PostMapping(value = "/saveMedicine")
	public ResponseStructure<Medicine> saveMedicineController(@RequestBody Medicine medicine) {
		return medicineService.saveMedicineService(medicine);
	}

	@GetMapping(value = "/getAllMedicine")
	public ResponseStructure<List<Medicine>> getAllMedicineController() {
		return medicineService.getAllMedicineService();
	}
	
	@GetMapping(value = "/getMedicineById/{medicineId}")
	public ResponseStructure<Medicine> getMedicineByIdController(@PathVariable int medicineId){
		return medicineService.getMedicineByIdService(medicineId);
	}
}
