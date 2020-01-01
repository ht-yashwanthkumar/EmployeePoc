package com.sapient.poc.utilities;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.sapient.poc.model.Employee;

/**
 * The {@code InMemoryCache} class serves as In Memory Cache after retrieving
 * data from CSV file during application startup.
 *
 * It cannot be instantiated as it is Singleton Class
 * 
 * Among the facilities provided by the {@code InMemoryCache} class are
 * retrieving ,updating,saving data
 * 
 * It ensures Thread Safety.
 * 
 */
public class InMemoryCache {

	private Map<Integer, Employee> employeeMap = new ConcurrentHashMap<Integer, Employee>();
	private static InMemoryCache cache;

	private InMemoryCache() {

	}

	/**
	 * Returns the instance of this class
	 */
	public static InMemoryCache getInMemoryCahceInstance() {
		if (cache == null) {
			synchronized (InMemoryCache.class) {
				if (cache == null) {
					cache = new InMemoryCache();
				}
			}
		}
		return cache;
	}

	/**
	 * method to return the employees which are stored in cache
	 * 
	 * @return Collection of employees
	 */

	public java.util.Collection<Employee> getEmployees() {
		return employeeMap.values();
	}

	/**
	 * Update the employees in cache
	 * 
	 * @param empList the employee list to be updated in cache
	 * 
	 */

	public void updateMap(List<Employee> empList) {
		empList.stream().forEach(emp -> {
			if (employeeMap.containsKey(emp.getEmpId())) {
				employeeMap.put(emp.getEmpId(), emp);
			}
		});
	}

	/**
	 * Save the employees in cache
	 * 
	 * @param empList the employee list to be saved in cache
	 * 
	 */

	public void saveEmployees(List<Employee> empList) {
		empList.stream().forEach(emp -> employeeMap.put(emp.getEmpId(), emp));
	}
}
