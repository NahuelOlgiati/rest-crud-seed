package ar.com.nolg.service.cdi;

import java.util.List;

import javax.inject.Named;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import javax.transaction.TransactionalException;

import ar.com.nolg.model.Employee;
import ar.com.nolg.service.EmployeeManager;

@Transactional
@Named("employeeManager")
public class EmployeeManagerCDI extends BasePersistenceManagerCDI<Employee> implements EmployeeManager {
	/**
	 */
	@Override
	public Class<Employee> getModelClass() {
		return Employee.class;
	}

	/**
	 */
	@Override
	public List<Employee> getAll() {

		List<Employee> employeeList = null;
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Employee> cq = cb.createQuery(getModelClass());
			cq.from(getModelClass());

			// Gets data.
			employeeList = getList(cq);
		} catch (Throwable t) {
			throw new TransactionalException(t.getMessage(), t);
		}
		return employeeList;
	}
}