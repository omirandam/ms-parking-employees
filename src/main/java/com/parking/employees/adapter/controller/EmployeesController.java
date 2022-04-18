package com.parking.employees.adapter.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.parking.employees.adapter.dto.EmployeeDto;
import com.parking.employees.adapter.dto.EmployeeRequest;
import com.parking.employees.application.port.in.IEmployeeCreate;
import com.parking.employees.application.port.in.IEmployeeDelete;
import com.parking.employees.application.port.in.IEmployeeFind;
import com.parking.employees.application.port.in.IEmployeeFindAll;
import com.parking.employees.application.port.in.IEmployeeUpdate;

@CrossOrigin(origins = "*", methods = {
		RequestMethod.OPTIONS,
		RequestMethod.GET,
		RequestMethod.POST,
		RequestMethod.DELETE,
		RequestMethod.PUT
})
@RestController
@RequestMapping("api")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class EmployeesController {

	@Autowired
    private IEmployeeFindAll findAllIn;
	@Autowired
    private IEmployeeFind findIn;
	@Autowired
    private IEmployeeCreate createIn;
	@Autowired
    private IEmployeeUpdate updateIn;
	@Autowired
    private IEmployeeDelete deleteIn;

	@GetMapping("/employee")
    public ResponseEntity<List<EmployeeDto>>findAll(){
    	 return new ResponseEntity<>(findAllIn.findAll(), HttpStatus.OK);
    }
	
	@GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeDto>find(@PathVariable Integer id){
    	 return new ResponseEntity<>(findIn.find(id), HttpStatus.OK);
    }
	
	@PostMapping("/employee")
    public ResponseEntity<String> create(@RequestBody @Valid EmployeeRequest employeeRequest){
		createIn.create(employeeRequest.toEntity(), employeeRequest.getIdrol());
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }
	
    @PutMapping("/employee/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id,@RequestBody @Valid EmployeeRequest employeeRequest){
    	updateIn.update(id, employeeRequest.toEntity(), employeeRequest.getIdrol());
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }
    
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
    	deleteIn.delete(id);
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }
	
}
