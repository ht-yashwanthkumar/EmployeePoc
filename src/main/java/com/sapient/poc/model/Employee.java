package com.sapient.poc.model;

/**
 * The {@code Employee} class serves as pojo
 */
public class Employee {

	int empId;
	String empName;
	String place;
	double salary;

	public Employee() {
		super();
	}

	public Employee(int empId, String empName, String place, Double salary) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.place = place;
		this.salary = salary;
	}

	public int getEmpId() {
		return empId;
	}

	public String getEmpName() {
		return empName;
	}

	public String getPlace() {
		return place;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

}
