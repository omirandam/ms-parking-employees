package com.parking.employees.adapter.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.parking.employees.adapter.dto.EmployeeDto;
import com.parking.employees.application.port.in.IEmployeeFindAll;


@CrossOrigin(origins = "*", methods = {
		RequestMethod.OPTIONS,
		RequestMethod.GET,
		RequestMethod.POST,
		RequestMethod.DELETE,
		RequestMethod.PUT
})
@RestController
@RequestMapping("api")
public class EmployeesController {


	@Autowired
    private IEmployeeFindAll findAll_in;
	
	
	@GetMapping("/employee")
    public ResponseEntity<List<EmployeeDto>>findAll(HttpServletRequest request){
    	 return new ResponseEntity<>(findAll_in.findAll(), HttpStatus.OK);
    }
	
}
