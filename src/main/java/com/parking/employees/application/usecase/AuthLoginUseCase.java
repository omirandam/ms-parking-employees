package com.parking.employees.application.usecase;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.parking.employees.application.port.in.IAuthLoginQuery;
import com.parking.employees.application.port.out.IEmployeeOut;
import com.parking.employees.application.util.JwtUtils;
import com.parking.employees.config.exception.ResourceNotFoundException;
import com.parking.employees.config.exception.UnauthorizedException;
import com.parking.employees.domain.AuthLogin;
import com.parking.employees.domain.Employee;

import lombok.SneakyThrows;

@Service
public class AuthLoginUseCase implements IAuthLoginQuery{

	private IEmployeeOut iemployeeOut;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private JwtUtils jwtUtils;
	
	@Value("${jwt.secret}")
	private String jwtSecret;
	
	public AuthLoginUseCase(IEmployeeOut iemployeeOut, BCryptPasswordEncoder bCryptPasswordEncoder, JwtUtils jwtUtils) {
		this.iemployeeOut = iemployeeOut;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.jwtUtils = jwtUtils;
		}

	@SneakyThrows
	@Override
	public String execute(AuthLogin authlogin) {
		String result =  null;
		Employee employee = iemployeeOut.findByUsername(authlogin.getUsername());
		if(employee != null) {
			if(bCryptPasswordEncoder.matches(authlogin.getPassword(), employee.getPaswword())) {
				String token = jwtUtils.createToken(employee.getUsername(), employee.getRol().getName(),jwtSecret);	
				result = token;
			}
			else {
				throw new UnauthorizedException("invalid username or password.");
			}
		}
		else {
			throw new ResourceNotFoundException("Employee not found with this username.");
		}
		return result;
	}

	
}