package com.parking.employees.config.exception;

import java.sql.Timestamp;

public class InternalServerErrorException extends GenericException {

private static final long serialVersionUID = 1L;
	
	public InternalServerErrorException(String errorMessage) {
		super(errorMessage);
	}
	public InternalServerErrorException(Timestamp timestamp, String errorMessage, String status) {
		super(timestamp,errorMessage,status);
	}
	
}
