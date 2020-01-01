package com.sapient.poc.services;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import com.sapient.poc.model.Employee;
import com.sapient.poc.utilities.InMemoryCache;
import com.sapient.poc.utilities.ResourceLoader;

@Service
public class EmployeeLoaderService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeLoaderService.class);

	@PostConstruct
	public void init() {
		LOGGER.info("Application started loading employees from csv file....");
		List<Employee> employeeList = ResourceLoader.loadObjectList(Employee.class, "employee.csv");
		if (employeeList.isEmpty()) {
			LOGGER.error("Employees Could Not Be Loaded.....");
			return;
		}
		loadEmployeesToCache(employeeList);
	}

	public void loadEmployeesToCache(List<Employee> employees) {
		InMemoryCache.getInMemoryCahceInstance().saveEmployees(employees);
	}
}
