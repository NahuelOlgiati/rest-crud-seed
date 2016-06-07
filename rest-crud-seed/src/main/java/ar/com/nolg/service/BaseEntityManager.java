package ar.com.nolg.service;

import javax.persistence.EntityManager;

public interface BaseEntityManager {
	/**
	 */
	public abstract EntityManager getEntityManager();
}