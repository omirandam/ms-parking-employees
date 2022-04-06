package com.parking.employees.adapter.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parking.employees.adapter.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
