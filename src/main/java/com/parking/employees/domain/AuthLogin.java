package com.parking.employees.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthLogin {
	
	private String username;
	
	private String password;
	

}
