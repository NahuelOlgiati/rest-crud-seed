package ar.com.nolg.service;

import ar.com.nolg.model.BaseModel;
import ar.com.nolg.service.exception.ServiceException;

public interface BasePersistenceManager<T extends BaseModel> extends BaseModelManager<T> {
	/**
	 */
	public abstract T save(T model) throws ServiceException;

	/**
	 */
	public abstract T delete(Long modelID) throws ServiceException;
}