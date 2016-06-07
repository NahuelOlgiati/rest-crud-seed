package ar.com.nolg.service.cdi;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.TransactionalException;

import ar.com.nolg.model.BaseModel;
import ar.com.nolg.service.BaseModelManager;
import ar.com.nolg.service.support.QueryHint;
import ar.com.nolg.service.support.QueryHintResult;
import ar.com.nolg.util.CompareUtil;
import ar.com.nolg.util.JPAUtil;

public abstract class BaseModelManagerCDI<T extends BaseModel> extends BaseEntityManagerCDI
		implements BaseModelManager<T> {
	/**
	 */
	@Override
	public final T get(final Long modelID) {
		T model = null;
		try {
			model = em.find(getModelClass(), modelID);
		} catch (Throwable t) {
			throw new TransactionalException(t.getMessage(), t);
		}
		return model;
	}

	/**
	 */
	@Override
	public final T getFULL(final Long modelID) {
		T model = null;
		try {
			if (!CompareUtil.isEmpty(model = get(modelID))) {
				// model.initLazyElements();
			}
		} catch (Throwable t) {
			throw new TransactionalException(t.getMessage(), t);
		}
		return model;
	}

	/**
	 */
	protected final <X> X getUnique(final CriteriaQuery<X> criteriaQuery) {
		X model = null;
		try {
			final TypedQuery<X> tq = em.createQuery(criteriaQuery);
			model = tq.getSingleResult();
		} catch (NoResultException n) {
		} catch (Throwable t) {
			throw new TransactionalException(t.getMessage(), t);
		}
		return model;
	}

	/**
	 */
	protected final <X> List<X> getList(final CriteriaQuery<X> criteriaQuery, final QueryHint queryHint) {
		List<X> modelList = null;
		try {
			final TypedQuery<X> tq = em.createQuery(criteriaQuery);
			tq.setFirstResult(queryHint.getFirstResult());
			tq.setMaxResults(queryHint.getMaxResults());
			modelList = tq.getResultList();
		} catch (Throwable t) {
			throw new TransactionalException(t.getMessage(), t);
		}
		return modelList;
	}

	/**
	 */
	protected final <X> QueryHintResult<X> getQueryHintResult(final CriteriaQuery<X> criteriaQuery,
			final QueryHint queryHint) {
		QueryHintResult<X> queryHintResult = null;

		List<X> list = getList(criteriaQuery, queryHint);

		Integer rowCount = 0;
		if (queryHint.isCountResults()) {
			rowCount = JPAUtil.count(em, criteriaQuery);
		}

		queryHintResult = new QueryHintResult<X>(rowCount, list);

		return queryHintResult;
	}

	/**
	 */
	protected final <X> List<X> getList(final CriteriaQuery<X> criteriaQuery) {
		List<X> modelList = null;
		try {
			final TypedQuery<X> tq = em.createQuery(criteriaQuery);
			modelList = tq.getResultList();
		} catch (Throwable t) {
			throw new TransactionalException(t.getMessage(), t);
		}
		return modelList;
	}
}