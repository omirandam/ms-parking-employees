package com.parking.employees.application.usecase;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.employees.adapter.dto.EmployeeDto;
import com.parking.employees.domain.Employee;
import com.parking.employees.application.port.in.IEmployeeFindAll;
import com.parking.employees.application.port.out.IEmployeeOut;

@Service
public class EmployeeFindAllUseCase implements IEmployeeFindAll {

	@Autowired
	private final IEmployeeOut iemployeeOut;
	
	public EmployeeFindAllUseCase(IEmployeeOut iemployeeOut) {
		this.iemployeeOut = iemployeeOut;
	}
	
	@Override
	public List<EmployeeDto> findAll(){
		List<Employee>listEmployees = iemployeeOut.findAll();
		return listEmployees.stream().map(EmployeeDto::of).collect(Collectors.toList());
	}
	
}
