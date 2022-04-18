package com.parking.employees.config.exception;

import java.sql.Timestamp;

public class UnauthorizedException extends GenericException {

private static final long serialVersionUID = 1L;

    public UnauthorizedException(String errorMessage) { 
	    super(errorMessage);
    }
	public UnauthorizedException(Timestamp timestamp, String errorMessage, String status) {
		super(timestamp,errorMessage,status);
	}
	
}
