package com.parking.employees.application.port.in;

import com.parking.employees.domain.Employee;

public interface IEmployeeCreate {
	
	public void create(Employee employee, Integer idRol);

}
