package com.parking.employees.application.port.in;

import com.parking.employees.domain.Employee;

public interface IEmployeeUpdate {

	public void update(Integer id, Employee employee, Integer idRol);
	
}
