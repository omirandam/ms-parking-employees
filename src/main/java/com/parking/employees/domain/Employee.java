package com.parking.employees.domain;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {

	private Integer id;

	private String identity_document;

	private String name;

	private String lastname;

	private LocalDate birthday;

	private Boolean state;
	
	private String username;

	private String paswword;
	
	private Rol rol;
	
	
}
