package com.sapient.poc.dao;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.sapient.poc.model.Employee;
import com.sapient.poc.utilities.InMemoryCache;

@Repository
public class EmployeeDao {

	public Optional<Employee> findById(int empId) {
		return InMemoryCache.getInMemoryCahceInstance().getEmployees().stream().filter(emp -> emp.getEmpId() == empId)
				.findAny();
	}

	public Optional<Collection<Employee>> find() {
		return Optional.of(InMemoryCache.getInMemoryCahceInstance().getEmployees());
	}

	public void updateEmployees(List<Employee> empList) {
		InMemoryCache.getInMemoryCahceInstance().updateMap(empList);
	}
}
