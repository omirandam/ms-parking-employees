package com.parking.employees.config.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.parking.employees.adapter.dto.ExceptionResponse;
import com.parking.employees.application.util.Time;


@ControllerAdvice
public class ErrorHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handlerNotFoundException(ResourceNotFoundException e, HttpServletRequest httpRequest) {
		ExceptionResponse result = new ExceptionResponse(Time.getTime(), "[Exception Response] - Exception: " + e.getMessage(), httpRequest.getRequestURI());
		return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionResponse> argumentNotValid(MethodArgumentNotValidException e,  HttpServletRequest httpRequest) {
		ExceptionResponse result = new ExceptionResponse(Time.getTime(), "[Exception Response] - Exception: " + e.getMessage(), httpRequest.getRequestURI());
		return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<ExceptionResponse> unauthorizedException(UnauthorizedException e,  HttpServletRequest httpRequest) {
		ExceptionResponse result = new ExceptionResponse(Time.getTime(), "[Exception Response] - Exception: " + e.getMessage(), httpRequest.getRequestURI());
		return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ExceptionResponse> accessDenied(AccessDeniedException e,  HttpServletRequest httpRequest) {
		ExceptionResponse result = new ExceptionResponse(Time.getTime(), "[Exception Response] - Exception: " + e.getMessage(), httpRequest.getRequestURI());
		return new ResponseEntity<>(result, HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> exception(Exception e,  HttpServletRequest httpRequest) {
		ExceptionResponse result = new ExceptionResponse(Time.getTime(), "[Exception Response] - Exception: " + e.getMessage(), httpRequest.getRequestURI());
		return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
