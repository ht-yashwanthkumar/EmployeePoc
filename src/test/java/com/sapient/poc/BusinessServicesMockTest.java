package com.sapient.poc;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.sapient.poc.dao.EmployeeDao;
import com.sapient.poc.exceptions.EmployeesNotFoundException;
import com.sapient.poc.model.Employee;
import com.sapient.poc.services.EmployeeService;

@RunWith(MockitoJUnitRunner.class)
public class BusinessServicesMockTest {

	@Mock
	EmployeeDao employeeDao;

	@InjectMocks
	EmployeeService employeeService;

	@Test
	public void testForEmployees() throws EmployeesNotFoundException {

		when(employeeDao.find()).thenReturn(Optional.of(getEmployeeMockData()));
		assertEquals(2, employeeService.retrieveEmployees().size());
	}

	@Test
	public void testForIncreasingSalary() throws EmployeesNotFoundException {
		Optional<Collection<Employee>> optData = Optional.of(getEmployeeMockData());
		when(employeeDao.find()).thenReturn(optData);
		employeeService.increaseSalary("Banglore", 10);

		Collection<Employee> empList = employeeService.retrieveEmployees();

		assertEquals(2200.0, empList.stream().filter(emp -> emp.getEmpId() == 101).findFirst().get().getSalary(), 0.0);
	}

	private Collection<Employee> getEmployeeMockData() {
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(new Employee(101, "yash", "Banglore", 2000.0));
		employees.add(new Employee(102, "vrush", "Hassan", 4000.0));
		return employees;
	}
}
