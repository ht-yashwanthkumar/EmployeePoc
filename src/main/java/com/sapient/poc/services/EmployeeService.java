package com.sapient.poc.services;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.poc.dao.EmployeeDao;
import com.sapient.poc.exceptions.EmployeesNotFoundException;
import com.sapient.poc.model.Employee;

/**
 * The {@code EmployeeService} class serves as service provider.
 * 
 * Business service
 */

@Service
public class EmployeeService {

	@Autowired
	EmployeeDao employeeDao;

	/**
	 * method to increase salary of each employee who belongs to specific location
	 * 
	 * @param place      employees salary which should be updated who belongs to
	 *                   this place
	 * @param percentage salary percentage which needs to be updated
	 */

	public void increaseSalary(String place, float percentage) throws EmployeesNotFoundException {
		Optional<Collection<Employee>> employees = employeeDao.find();
		if (!employees.isPresent()) {
			throw new EmployeesNotFoundException("Employees Not Found");
		}

		List<Employee> updatedEmployeeList = employees.get().stream().filter(emp -> emp.getPlace().equals(place))
				.map(emp -> {
					emp.setSalary(emp.getSalary() + emp.getSalary() / percentage);
					return emp;
				}).collect(Collectors.toList());

		employeeDao.updateEmployees(updatedEmployeeList);
	}

	/**
	 * method to retrieve all employees
	 * 
	 */

	public Collection<Employee> retrieveEmployees() throws EmployeesNotFoundException {
		Optional<Collection<Employee>> optEmployees = employeeDao.find();
		if (!optEmployees.isPresent()) {
			throw new EmployeesNotFoundException("Employees Not Found");
		}
		return optEmployees.get();
	}

	/**
	 * method to retrieve specific employee
	 * 
	 * @param empId id to retrieve specific employee
	 * @return respective employee will be returned
	 */

	public Employee findEmployeeById(int empId) throws EmployeesNotFoundException {

		Optional<Employee> optEmployee = employeeDao.findById(empId);
		if (!optEmployee.isPresent()) {
			throw new EmployeesNotFoundException("Employee Not Found");
		}
		return optEmployee.get();
	}
}
