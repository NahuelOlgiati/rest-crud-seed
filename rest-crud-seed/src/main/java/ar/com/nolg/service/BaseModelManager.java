package ar.com.nolg.service;

import ar.com.nolg.model.BaseModel;

public interface BaseModelManager<T extends BaseModel> extends BaseEntityManager {
	/**
	 */
	public abstract Class<T> getModelClass();

	/**
	 */
	public abstract T get(Long modelID);

	/**
	 */
	public abstract T getFULL(Long modelID);
}