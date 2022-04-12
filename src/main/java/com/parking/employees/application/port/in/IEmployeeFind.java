package com.parking.employees.application.port.in;

import com.parking.employees.adapter.dto.EmployeeDto;

public interface IEmployeeFind {
	
	public EmployeeDto find(Integer id);
	
}
