package com.sapient.poc.utilities;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.sapient.poc.model.Employee;


public class InMemoryCache {

	private Map<Integer,Employee> employeeMap = new ConcurrentHashMap<Integer,Employee>();
	private static InMemoryCache cache;

	private InMemoryCache() {

	}

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

	public java.util.Collection<Employee> getEmployees() {
		return  employeeMap.values();
	}

	public void updateMap(List<Employee> empList) {
		empList.stream().forEach(emp->{
			if(employeeMap.containsKey(emp.getEmpId())) {
				employeeMap.put(emp.getEmpId(), emp);
			}
		});
	}

	public void saveEmployees(List<Employee> empList) {
		empList.stream().forEach(emp-> employeeMap.put(emp.getEmpId(), emp));
	}
}
