package com.parking.employees.config.exception;

import java.sql.Timestamp;

public class ResourceNotFoundException extends GenericException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String errorMessage) {
		super(errorMessage);
	}
	public ResourceNotFoundException(Timestamp timestamp, String errorMessage, String status) {
		super(timestamp,errorMessage,status);
	}

	

}
