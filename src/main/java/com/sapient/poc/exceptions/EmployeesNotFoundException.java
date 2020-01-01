package com.sapient.poc.exceptions;

/**
 * The {@code EmployeesNotFoundException} class serves as custom exception
 */

public class EmployeesNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -16275082187971431L;
	String message;

	public EmployeesNotFoundException(String msg) {
		super(msg);
	}
}
