package com.parking.employees.adapter.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {

	private Timestamp timestamp;
	private String data;
	private String requestUrl;
	
}
