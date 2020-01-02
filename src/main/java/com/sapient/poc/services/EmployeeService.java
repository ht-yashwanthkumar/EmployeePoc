package com.sapient.poc.services;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

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
			LOGGER.error("Couldn't Find Employees");
			throw new EmployeesNotFoundException("Couldn't Find Employees");
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
			LOGGER.error("Couldn't Find Employees");
			throw new EmployeesNotFoundException("Couldn't Find Employees");
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
			LOGGER.error("Couldn't Find Employee With Id {}",empId);
			throw new EmployeesNotFoundException("Couldn't Find Employee");
		}
		return optEmployee.get();
	}
}
