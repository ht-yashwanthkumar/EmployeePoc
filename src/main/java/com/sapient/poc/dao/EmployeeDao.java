package com.sapient.poc.dao;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.sapient.poc.model.Employee;
import com.sapient.poc.utilities.InMemoryCache;

/**
 * The {@code EmployeeDao} class serves as data provider.
 * 
 * Repository service
 */
@Repository
public class EmployeeDao {

	/**
	 * method to retrieve specific employee
	 * 
	 * @param empId id to retrieve specific employee
	 * @return respective employee will be returned
	 */
	public Optional<Employee> findById(int empId) {
		return InMemoryCache.getInMemoryCahceInstance().getEmployees().stream().filter(emp -> emp.getEmpId() == empId)
				.findAny();
	}

	/**
	 * method to retrieve all employees
	 * 
	 */
	public Optional<Collection<Employee>> find() {
		return Optional.of(InMemoryCache.getInMemoryCahceInstance().getEmployees());
	}

	/**
	 * method to update employees
	 * 
	 * @param empList to update
	 */
	public void updateEmployees(List<Employee> empList) {
		InMemoryCache.getInMemoryCahceInstance().updateMap(empList);
	}
}
