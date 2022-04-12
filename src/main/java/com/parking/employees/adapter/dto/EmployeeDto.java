package com.parking.employees.adapter.dto;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.parking.employees.domain.Employee;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDto {

	public Integer id;

	public String identity_document;

	public String name;

	public String lastname;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public LocalDate birthday;

	public Boolean state;
	
	public String username;

	public String paswword;
	
	public RolDto rol;
	
	public Employee toEntity() {
		Employee employee = new Employee();
		BeanUtils.copyProperties(this, employee);
		if(this.rol != null) {
			employee.setRol(this.rol.toEntity());
		}
		return employee;
	}
	
	public static EmployeeDto of(Employee employee) {
		EmployeeDto dto = new EmployeeDto();
		BeanUtils.copyProperties(employee, dto);
		if(employee.getRol() != null) {
			RolDto rolDto = new RolDto();
			BeanUtils.copyProperties(employee.getRol(), rolDto);
			dto.setRol(rolDto);
		}
		return dto;
	}
	
}
