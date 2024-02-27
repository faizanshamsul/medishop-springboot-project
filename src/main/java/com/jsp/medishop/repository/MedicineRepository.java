package com.jsp.medishop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.medishop.dto.Medicine;

public interface MedicineRepository extends JpaRepository<Medicine, Integer>{
	
}
