package ar.com.nolg.service.util;

import javax.persistence.criteria.CriteriaBuilder;

public abstract class ExpressionBuilder {
	protected CriteriaBuilder cb;

	/**
	 */
	protected ExpressionBuilder(CriteriaBuilder cb) {
		this.cb = cb;
	}
}