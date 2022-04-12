package com.parking.employees.adapter.dto;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.parking.employees.config.exception.InternalServerErrorException;
import com.parking.employees.domain.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeRequest {

	@NotNull(message = "The identity document cannot be empty")
	public String identity_document;

	@NotNull(message = "The name cannot be empty")
	public String name;

	@NotNull(message = "The lastname cannot be empty")
	public String lastname;

	@NotNull(message = "The birthday cannot be empty")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public LocalDate birthday;

	@NotNull(message = "The state cannot be empty")
	public Boolean state;
	
	@NotNull(message = "The username cannot be empty")
	public String username;

	@NotNull(message = "The paswword cannot be empty")
	public String paswword;
	
	@NotNull(message = "The idrol cannot be empty")
	public Integer idrol;
	
	public Employee toEntity() {
		Employee employee = new Employee();
		if(this.isValidateDate()) {
			BeanUtils.copyProperties(this, employee);
			return employee;
		}
		return null;
	}
	
	private Boolean isValidateDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		 try {
	          format.parse(this.birthday.toString());
	          return true;
	     }
	     catch(Exception e){
			    throw new InternalServerErrorException(e.getMessage());
	     }
		 
	}
	
}
