package com.parking.employees.application.usecase;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.employees.application.port.in.IEmployeeDelete;
import com.parking.employees.application.port.out.IEmployeeOut;
import com.parking.employees.config.exception.InternalServerErrorException;
import com.parking.employees.config.exception.ResourceNotFoundException;

@Service
public class EmployeeDeleteUseCase implements IEmployeeDelete {

	@Autowired
	private IEmployeeOut iemployeeOut;
	
	@Override
	public void delete(Integer id){
		try {
			iemployeeOut.delete(id);			
		} 
		catch (ResourceNotFoundException exception) {
			throw exception;
	    } 
		catch (Exception e) {
		    throw new InternalServerErrorException(e.getMessage());
	    }
	}
	
}
