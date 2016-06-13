package ar.com.nolg.service.util;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

import ar.com.nolg.model.BaseModel;
import ar.com.nolg.util.CompareUtil;

public class PredicateBuilder extends ExpressionBuilder {
	/**
	 */
	public PredicateBuilder(CriteriaBuilder cb) {
		super(cb);
	}

	/**
	 */
	public Predicate like(Expression<String> expression, String value) {
		Predicate p = cb.conjunction();

		if (!CompareUtil.isEmpty(value)) {
			StringBuilder sb = new StringBuilder();
			sb.append("%").append(value.trim().replaceAll(" ", "%")).append("%");
			p = cb.like(cb.upper(cb.trim(expression)), sb.toString().toUpperCase());
		}
		return p;
	}

	/**
	 */
	public Predicate equal(Expression<String> expression, String value) {
		Predicate p = cb.conjunction();

		if (!CompareUtil.isEmpty(value)) {
			p = cb.equal(expression, value);
		}
		return p;
	}

	/**
	 */
	public Predicate equal(Expression<Integer> expression, Integer value) {
		Predicate p = cb.conjunction();

		if (!CompareUtil.isEmpty(value)) {
			p = cb.equal(expression, value);
		}
		return p;
	}

	/**
	 */
	public Predicate equal(Expression<Long> expression, Long value) {
		Predicate p = cb.conjunction();

		if (!CompareUtil.isEmpty(value)) {
			p = cb.equal(expression, value);
		}
		return p;
	}

	/**
	 */
	public Predicate equal(Expression<Double> expression, Double value) {
		Predicate p = cb.conjunction();

		if (!CompareUtil.isEmpty(value)) {
			p = cb.equal(expression, value);
		}
		return p;
	}

	/**
	 */
	public Predicate equal(Expression<BigDecimal> expression, BigDecimal value) {
		Predicate p = cb.conjunction();

		if (!CompareUtil.isEmpty(value)) {
			p = cb.equal(expression, value);
		}
		return p;
	}

	/**
	 */
	public Predicate equal(Expression<Boolean> expression, Boolean value) {
		Predicate p = cb.conjunction();

		if (!CompareUtil.isEmpty(value)) {
			p = cb.equal(expression, value);
		}
		return p;
	}

	/**
	 */
	public <T extends Enum<?>> Predicate equal(Expression<T> expression, T value) {
		Predicate p = cb.conjunction();

		if (!CompareUtil.isEmpty(value)) {
			p = cb.equal(expression, value);
		}
		return p;
	}

	/**
	 */
	public <T extends BaseModel> Predicate equal(Expression<T> expression, T value) {
		Predicate p = cb.conjunction();

		if (!CompareUtil.isEmpty(value)) {
			p = cb.equal(expression, value);
		}
		return p;
	}

	/**
	 * Creates an inclusive between expression. If range parameters are missing
	 * they resolve to a true expression.
	 */
	public <Y extends java.lang.Comparable<? super Y>> Predicate between(Expression<Y> expression,
			Y from, Y to) {
		Predicate pa = cb.conjunction();
		Predicate pb = cb.conjunction();

		if (!CompareUtil.isEmpty(from)) {
			pa = cb.greaterThanOrEqualTo(expression, from);
		}
		if (!CompareUtil.isEmpty(to)) {
			pb = cb.lessThanOrEqualTo(expression, to);
		}
		return cb.and(pa, pb);
	}

	/**
	 * Creates an inclusive between expression. If range parameters are missing
	 * they resolve to a true expression.
	 */
	public Predicate between(Expression<BigDecimal> expression, BigDecimal from,
			BigDecimal to) {
		Predicate pa = cb.conjunction();
		Predicate pb = cb.conjunction();

		if (!CompareUtil.isEmpty(from)) {
			pa = cb.greaterThanOrEqualTo(expression, from);
		}
		if (!CompareUtil.isEmpty(to)) {
			pb = cb.lessThanOrEqualTo(expression, to);
		}
		return cb.and(pa, pb);
	}

	/**
	 */
	public <T> Predicate in(Expression<T> expression, List<T> values) {
		Predicate p = cb.conjunction();

		if (!CompareUtil.isEmpty(values)) {
			if (values.size() == 1) {
				p = cb.equal(expression, values.get(0));
			} else {
				p = expression.in(values);
			}
		}
		return p;
	}
}