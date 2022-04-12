package com.parking.employees.application.usecase;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.employees.adapter.dto.EmployeeDto;
import com.parking.employees.domain.Employee;
import com.parking.employees.application.port.in.IEmployeeFind;
import com.parking.employees.application.port.out.IEmployeeOut;

@Service
public class EmployeeFindUseCase implements IEmployeeFind {

	@Autowired
	private IEmployeeOut iemployeeOut;
	
	@Override
	public EmployeeDto find(Integer id){
		Employee employee = iemployeeOut.find(id);
		return EmployeeDto.of(employee);
	}
	
}
