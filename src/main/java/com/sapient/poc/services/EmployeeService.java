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

@Service
public class EmployeeService {

	@Autowired
	EmployeeDao employeeDao;

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

	public Collection<Employee> retrieveEmployees() throws EmployeesNotFoundException {
		Optional<Collection<Employee>> optEmployees = employeeDao.find();
		if (!optEmployees.isPresent()) {
			throw new EmployeesNotFoundException("Employees Not Found");
		}
		return optEmployees.get();
	}

	public Employee findEmployeeById(int empId) throws EmployeesNotFoundException {

		Optional<Employee> optEmployee = employeeDao.findById(empId);
		if (!optEmployee.isPresent()) {
			throw new EmployeesNotFoundException("Employee Not Found");
		}
		return optEmployee.get();
	}
}
