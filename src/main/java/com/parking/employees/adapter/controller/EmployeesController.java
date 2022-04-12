package com.parking.employees.adapter.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class EmployeesController {

	@Autowired
    private IEmployeeFindAll findAll_in;
	@Autowired
    private IEmployeeFind find_in;
	@Autowired
    private IEmployeeCreate create_in;
	@Autowired
    private IEmployeeUpdate update_in;
	@Autowired
    private IEmployeeDelete delete_in;

	
	@GetMapping("/employee")
    public ResponseEntity<List<EmployeeDto>>findAll(){
    	 return new ResponseEntity<>(findAll_in.findAll(), HttpStatus.OK);
    }
	
	@GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeDto>find(@PathVariable Integer id){
    	 return new ResponseEntity<>(find_in.find(id), HttpStatus.OK);
    }
	
	@PostMapping("/employee")
    public ResponseEntity<String> create(@RequestBody @Valid EmployeeRequest employeeRequest){
		create_in.create(employeeRequest.toEntity(), employeeRequest.getIdrol());
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id,@RequestBody @Valid EmployeeRequest employeeRequest){
    	update_in.update(id, employeeRequest.toEntity(), employeeRequest.getIdrol());
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }
    
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
    	delete_in.delete(id);
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }
	
}
