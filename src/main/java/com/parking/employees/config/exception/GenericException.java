package com.parking.employees.config.exception;

import java.sql.Timestamp;

public class GenericException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private Timestamp timestamp;
	private String errorMessage;
	private String status;
	
	public GenericException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	public GenericException(Timestamp timestamp, String errorMessage, String status) {
		super(errorMessage);
		this.timestamp = timestamp;
		this.errorMessage = errorMessage;
		this.status = status;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
