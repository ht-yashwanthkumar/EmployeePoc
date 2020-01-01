package com.sapient.poc.exceptions;

public class EmployeesNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -16275082187971431L;
	String message;

	public EmployeesNotFoundException(String msg) {
		super(msg);
	}
}
