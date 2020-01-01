package com.sapient.poc.exceptions;

import java.util.Date;

/**
 * The {@code ExceptionResponse} class serves as response entity to return
 * exceptions in detail
 */
public class ExceptionResponse {

	private Date timeStamp;
	String message;
	String details;

	public ExceptionResponse(Date timeStamp, String message, String details) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.details = details;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}
}
