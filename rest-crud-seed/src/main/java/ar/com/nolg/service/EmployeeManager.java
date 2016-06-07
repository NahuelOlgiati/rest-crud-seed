package ar.com.nolg.service;

import java.util.List;

import ar.com.nolg.model.Employee;

public interface EmployeeManager extends BaseModelManager<Employee> {

	/**	
	 */
	public abstract List<Employee> getAll();
}