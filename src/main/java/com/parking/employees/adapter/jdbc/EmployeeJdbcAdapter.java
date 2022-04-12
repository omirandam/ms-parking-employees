package com.parking.employees.adapter.jdbc;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.employees.application.port.out.IEmployeeOut;
import com.parking.employees.config.exception.InternalServerErrorException;
import com.parking.employees.config.exception.ResourceNotFoundException;
import com.parking.employees.domain.Employee;
import com.parking.employees.domain.Rol;
import com.parking.employees.adapter.entity.EmployeeEntity;
import com.parking.employees.adapter.entity.RolEntity;
import com.parking.employees.adapter.jpa.EmployeeRepository;
import com.parking.employees.adapter.jpa.RolRepository;

@Service
public class EmployeeJdbcAdapter implements IEmployeeOut {


	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private RolRepository rolRepository;
	
	public EmployeeJdbcAdapter(EmployeeRepository employeeRepository, RolRepository rolRepository) {
		this.employeeRepository = employeeRepository;
		this.rolRepository = rolRepository;
	}

	@Override
	public Employee find(Integer id){
		Optional<EmployeeEntity>optional = employeeRepository.findById(id);
		if(optional.isPresent()) {
			Employee employee = new Employee();
			Rol rol = new Rol();
			BeanUtils.copyProperties(optional.get(), employee);
			BeanUtils.copyProperties(optional.get().getRol(), rol);
			employee.setRol(rol);
			return employee;
		}
		else
			throw new ResourceNotFoundException("There is no house with that id.");
	}
	
	@Override
	public List<Employee> findAll(){		
		return employeeRepository.findAll().stream().map(item->{
			Employee employee = new Employee();
			Rol rol = new Rol();
			BeanUtils.copyProperties(item, employee);
			BeanUtils.copyProperties(item.getRol(), rol);
			employee.setRol(rol);
			return employee;
		}).collect(Collectors.toList());
	}
	
	
	@Override
	public void delete(Integer id) {
		try {
			Optional<EmployeeEntity>optionalHouse = employeeRepository.findById(id);
			if(optionalHouse.isPresent()) {
				employeeRepository.delete(optionalHouse.get());
			}
			else
				throw new ResourceNotFoundException("There is no house with that id.");
	    }
		catch (ResourceNotFoundException exception) {
			throw exception;
	    } 
		catch (Exception e) {
		    throw new InternalServerErrorException(e.getMessage());
	    }
	
	}

	@Override
	public void update(Integer id, Employee employee) {
		try {
			Optional<EmployeeEntity>optionalEmployee = employeeRepository.findById(id);
			if(optionalEmployee.isPresent()) {
				EmployeeEntity employeeEntity = new EmployeeEntity();
				BeanUtils.copyProperties(optionalEmployee.get(), employeeEntity);
				employeeEntity.setBirthday(employee.getBirthday());
				employeeEntity.setIdentity_document(employee.getIdentity_document());
				employeeEntity.setLastname(employee.getLastname());
				employeeEntity.setName(employee.getName());
				employeeEntity.setPaswword(employee.getPaswword());
				employeeEntity.setState(employee.getState());
				employeeEntity.setUsername(employee.getUsername());

				RolEntity rolEntity = rolRepository.findById(employee.getRol().getId()).get();
				employeeEntity.setRol(rolEntity);
				
				employeeRepository.save(employeeEntity);
			}
			else
				throw new ResourceNotFoundException("There is no house with that id.");
	    }
		catch (ResourceNotFoundException exception) {
			throw exception;
	    } 
		catch (Exception e) {
		    throw new InternalServerErrorException(e.getMessage());
	    }
	
	}

	public void create(Employee employee) {
		try {
			EmployeeEntity employeeEntity = new EmployeeEntity();
			RolEntity rolEntity = new RolEntity();
			
			BeanUtils.copyProperties(employee, employeeEntity);
			BeanUtils.copyProperties(employee.getRol(), rolEntity);
			
			employeeEntity.setRol(rolEntity);
			
			employeeRepository.save(employeeEntity);
	    }
		catch (ResourceNotFoundException exception) {
			throw exception;
	    } 
		catch (Exception e) {
		    throw new InternalServerErrorException(e.getMessage());
	    }
	
	}
	
}
