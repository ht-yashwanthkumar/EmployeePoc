package com.sapient.poc.controllers;

import java.net.URI;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sapient.poc.exceptions.EmployeesNotFoundException;
import com.sapient.poc.model.Employee;
import com.sapient.poc.services.EmployeeService;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

/**
 * The {@code EmployeeRestController} class serves as rest resource provider of
 * Employee
 */
@RestController
public class EmployeeRestController {

	/**
	 * Injecting {@code EmployeeService} to invoke business related operations.
	 */
	@Autowired
	EmployeeService empService;

	/**
	 * method to return all the employees
	 * 
	 * @return ResponseEntity<Collection<Employee>> returns collection of Employees
	 *         in response entity to clients
	 * 
	 */
	@GetMapping(path = "/employees")
	public ResponseEntity<Collection<Employee>> getEmployees() throws EmployeesNotFoundException {

		return new ResponseEntity<Collection<Employee>>(empService.retrieveEmployees(), HttpStatus.OK);
	}

	/**
	 * method to return specific employee
	 * 
	 * @param empId id of the employee
	 * @return ResponseEntity<Employee> returns respective Employee as resource to
	 *         clients.
	 * 
	 */

	@GetMapping(path = "/employee/{empId}")
	public Resource<Employee> getEmployee(@PathVariable int empId) throws EmployeesNotFoundException {

		Resource<Employee> resource = new Resource<Employee>(empService.findEmployeeById(empId));
		ControllerLinkBuilder linkTo = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getEmployees());
		resource.add(linkTo.withRel("al-employees"));
		return resource;
	}

	/**
	 * method to increase salary of each employee who belongs to specific location
	 * 
	 * @param place      employees salary which should be updated who belongs to
	 *                   this place
	 * @param percentage salary percentage which needs to be updated
	 */
	@PutMapping(path = "employee/place/{place}/salary/{percentage}")
	public ResponseEntity<?> increaseSalary(@PathVariable String place, @PathVariable float percentage)
			throws EmployeesNotFoundException {
		empService.increaseSalary(place, percentage);
		URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/employees").build().toUri();
		return ResponseEntity.accepted().location(location).build();
	}
}
