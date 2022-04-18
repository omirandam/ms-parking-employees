package com.parking.employees.adapter.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.parking.employees.adapter.dto.AuthLoginRequest;
import com.parking.employees.application.port.in.IAuthLoginQuery;

@CrossOrigin(origins = "*", methods = {
		RequestMethod.OPTIONS,
		RequestMethod.GET,
		RequestMethod.POST,
		RequestMethod.DELETE,
		RequestMethod.PUT
})
@RestController
@RequestMapping("auth")
public class AuthController {
	
	
	private final IAuthLoginQuery authLogin;
	
	public AuthController(IAuthLoginQuery authLogin) {
		this.authLogin = authLogin;
	}
	
	
	@PostMapping("/login")
    public ResponseEntity<String> create(@RequestBody @Valid AuthLoginRequest authLoginRequest){
		String token = authLogin.execute(authLoginRequest.toDomain());
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

}
