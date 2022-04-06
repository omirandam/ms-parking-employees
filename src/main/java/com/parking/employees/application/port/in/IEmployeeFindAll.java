package com.parking.employees.application.port.in;

import java.util.List;

import com.parking.employees.adapter.dto.EmployeeDto;

public interface IEmployeeFindAll {

	public List<EmployeeDto> findAll();
}
