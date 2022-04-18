package com.parking.employees.adapter.dto;


import javax.validation.constraints.NotBlank;

import org.springframework.beans.BeanUtils;

import com.parking.employees.domain.AuthLogin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthLoginRequest {
	
	@NotBlank(message = "The username cannot be empty")
	private String username;

	@NotBlank(message = "The password cannot be empty")
	private String password;
	
	public AuthLogin toDomain() {
		AuthLogin authLogin = new AuthLogin();
		BeanUtils.copyProperties(this, authLogin);
		
		return authLogin;
	}

}
