package com.parking.employees.adapter.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.employees.application.port.out.IEmployeeOut;
import com.parking.employees.adapter.entity.Employee;
import com.parking.employees.adapter.jpa.EmployeeRepository;

@Service
public class EmployeeJdbcAdapter implements IEmployeeOut {


	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public List<Employee> findAll(){
		return employeeRepository.findAll();
	}
	
}
