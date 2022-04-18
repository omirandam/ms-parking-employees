package com.parking.employees.application.port.out;

import java.util.List;

import com.parking.employees.domain.Employee;

public interface IEmployeeOut {

	public List<Employee> findAll();
	
	public void create(Employee employee);

	public void delete(Integer id);
	
	public Employee find(Integer id);

	public void update(Integer id, Employee employee);
	
	public Employee findByUsername(String username); 
	
}
