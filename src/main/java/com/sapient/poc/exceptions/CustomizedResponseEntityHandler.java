package com.sapient.poc.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * The {@code CustomizedResponseEntityHandler} class serves custom exception
 * handling to all exceptions generated
 */
@ControllerAdvice
public class CustomizedResponseEntityHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ EmployeesNotFoundException.class, Exception.class })
	public ResponseEntity<?> handleEmployeesNotFoundException(Exception ex, WebRequest webRequest) {
		ExceptionResponse eResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				webRequest.getDescription(false));

		if (ex instanceof EmployeesNotFoundException) {
			return new ResponseEntity<>(eResponse, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(eResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
