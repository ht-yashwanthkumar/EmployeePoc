package com.sapient.poc.services;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import com.sapient.poc.model.Employee;
import com.sapient.poc.utilities.InMemoryCache;
import com.sapient.poc.utilities.ResourceLoader;

/**
 * The {@code EmployeeLoaderService} class serves as service to take up task to
 * load data from CSV file during application task.
 */

@Service
public class EmployeeLoaderService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeLoaderService.class);

	/**
	 * init method to take responsibility to load CSV data during application
	 * startup
	 */
	@PostConstruct
	public void init() {
		LOGGER.info("Application started loading employees from csv file....");
		List<Employee> employeeList = ResourceLoader.loadEmployeeInfoFromFile(Employee.class, "employee.csv");
		if (employeeList.isEmpty()) {
			LOGGER.error("Employees Could Not Be Loaded.....");
			return;
		}

		LOGGER.info("Loading employees from csv file is completed....");
		loadEmployeesToCache(employeeList);
	}

	/**
	 * method to load data to cache after successfully retrieving data from CSV
	 * 
	 * @param employees the data to be stored in cache
	 * 
	 */
	public void loadEmployeesToCache(List<Employee> employees) {
		LOGGER.info("Caching the data");
		InMemoryCache.getInMemoryCahceInstance().saveEmployees(employees);
	}
}
