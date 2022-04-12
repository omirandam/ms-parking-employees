package com.parking.employees.domain;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {

	public Integer id;

	public String identity_document;

	public String name;

	public String lastname;

	public LocalDate birthday;

	public Boolean state;
	
	public String username;

	public String paswword;
	
	public Rol rol;
	
	
}
