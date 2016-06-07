package ar.com.nolg.service.util;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

import ar.com.nolg.model.BaseModel;
import ar.com.nolg.util.CompareUtil;

public final class PredicateBuilder extends ExpressionBuilder {
	/**
	 */
	public PredicateBuilder(CriteriaBuilder cb) {
		super(cb);
	}

	/**
	 */
	public final Predicate like(final Expression<String> expression, final String value) {
		Predicate p = cb.conjunction();

		if (!CompareUtil.isEmpty(value)) {
			final StringBuilder sb = new StringBuilder();
			sb.append("%").append(value.trim().replaceAll(" ", "%")).append("%");
			p = cb.like(cb.upper(cb.trim(expression)), sb.toString().toUpperCase());
		}
		return p;
	}

	/**
	 */
	public final Predicate equal(final Expression<String> expression, final String value) {
		Predicate p = cb.conjunction();

		if (!CompareUtil.isEmpty(value)) {
			p = cb.equal(expression, value);
		}
		return p;
	}

	/**
	 */
	public final Predicate equal(final Expression<Integer> expression, final Integer value) {
		Predicate p = cb.conjunction();

		if (!CompareUtil.isEmpty(value)) {
			p = cb.equal(expression, value);
		}
		return p;
	}

	/**
	 */
	public final Predicate equal(final Expression<Long> expression, final Long value) {
		Predicate p = cb.conjunction();

		if (!CompareUtil.isEmpty(value)) {
			p = cb.equal(expression, value);
		}
		return p;
	}

	/**
	 */
	public final Predicate equal(final Expression<Double> expression, final Double value) {
		Predicate p = cb.conjunction();

		if (!CompareUtil.isEmpty(value)) {
			p = cb.equal(expression, value);
		}
		return p;
	}

	/**
	 */
	public final Predicate equal(final Expression<BigDecimal> expression, final BigDecimal value) {
		Predicate p = cb.conjunction();

		if (!CompareUtil.isEmpty(value)) {
			p = cb.equal(expression, value);
		}
		return p;
	}

	/**
	 */
	public final Predicate equal(final Expression<Boolean> expression, final Boolean value) {
		Predicate p = cb.conjunction();

		if (!CompareUtil.isEmpty(value)) {
			p = cb.equal(expression, value);
		}
		return p;
	}

	/**
	 */
	public final <T extends Enum<?>> Predicate equal(final Expression<T> expression, final T value) {
		Predicate p = cb.conjunction();

		if (!CompareUtil.isEmpty(value)) {
			p = cb.equal(expression, value);
		}
		return p;
	}

	/**
	 */
	public final <T extends BaseModel> Predicate equal(final Expression<T> expression, final T value) {
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
	public final <Y extends java.lang.Comparable<? super Y>> Predicate between(final Expression<Y> expression,
			final Y from, final Y to) {
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
	public final Predicate between(final Expression<BigDecimal> expression, final BigDecimal from,
			final BigDecimal to) {
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
	public final <T> Predicate in(final Expression<T> expression, final List<T> values) {
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