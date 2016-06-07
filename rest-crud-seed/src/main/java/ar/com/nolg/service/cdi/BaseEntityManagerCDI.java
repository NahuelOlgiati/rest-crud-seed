package ar.com.nolg.service.cdi;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ar.com.nolg.service.BaseEntityManager;

public abstract class BaseEntityManagerCDI implements BaseEntityManager {
	@PersistenceContext
	protected EntityManager em;

	/**
	 */
	@Override
	public EntityManager getEntityManager() {
		return em;
	}
}