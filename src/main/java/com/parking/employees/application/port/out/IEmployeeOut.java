package com.parking.employees.application.port.out;

import java.util.List;

import com.parking.employees.adapter.entity.Employee;

public interface IEmployeeOut {


	public List<Employee> findAll();
}
