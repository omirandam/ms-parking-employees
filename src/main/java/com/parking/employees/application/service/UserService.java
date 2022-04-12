package com.parking.employees.application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.parking.employees.adapter.entity.EmployeeEntity;
import com.parking.employees.adapter.jpa.EmployeeRepository;


@Service
public class UserService implements UserDetailsService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		EmployeeEntity employeeEntity = employeeRepository.findByUsername(username);
		
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		roles.add(new SimpleGrantedAuthority(employeeEntity.getRol().getName()));
		
		UserDetails userDetails = new User(employeeEntity.getUsername(), employeeEntity.getPaswword(), roles);
		
		return userDetails;
	}

}
