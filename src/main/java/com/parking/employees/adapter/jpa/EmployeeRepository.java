package com.parking.employees.adapter.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parking.employees.adapter.entity.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {

	EmployeeEntity findByUsername(String username);
	
}
