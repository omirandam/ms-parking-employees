package com.parking.employees.application.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.parking.employees.application.port.in.IEmployeeCreate;
import com.parking.employees.application.port.out.IEmployeeOut;
import com.parking.employees.application.service.RolService;
import com.parking.employees.config.exception.InternalServerErrorException;
import com.parking.employees.config.exception.ResourceNotFoundException;
import com.parking.employees.domain.Employee;
import com.parking.employees.domain.Rol;

@Service
public class EmployeeCreateUseCase implements IEmployeeCreate {

	@Autowired
	private IEmployeeOut iemployeeOut;	
	
	@Autowired
	private RolService rolservice;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	public void create(Employee employee, Integer idRol){
		try {
			Rol rol = rolservice.findById(idRol);
			employee.setRol(rol);
			employee.setPaswword(encoder.encode(employee.getPaswword()));
			
			iemployeeOut.create(employee);			
		} 
		catch (ResourceNotFoundException exception) {
			throw exception;
	    } 
		catch (Exception e) {
		    throw new InternalServerErrorException(e.getMessage());
	    }
	}
	
}
