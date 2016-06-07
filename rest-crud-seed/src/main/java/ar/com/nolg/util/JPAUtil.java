package ar.com.nolg.util;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

public class JPAUtil {
	private static volatile long aliasCount = 0;

	/** 
	 */
	public static <T> Integer count(EntityManager em, CriteriaQuery<T> criteria) {
		return em.createQuery(countCriteria(em, criteria)).getSingleResult().intValue();
	}

	/**
	 */
	public static <T> CriteriaQuery<Long> countCriteria(EntityManager em, CriteriaQuery<T> criteria) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
		copyCriteriaNoSelection(criteria, countCriteria);

		if (criteria.isDistinct()) {
			countCriteria.select(builder.countDistinct(findRoot(countCriteria, criteria.getResultType())));
		} else {
			countCriteria.select(builder.count(findRoot(countCriteria, criteria.getResultType())));
		}

		return countCriteria;
	}

	/**
	 */
	public static void copyCriteriaNoSelection(CriteriaQuery<?> from, CriteriaQuery<?> to) {

		for (Root<?> root : from.getRoots()) {

			Root<?> dest = to.from(root.getJavaType());

			dest.alias(getOrCreateAlias(root));

			copyJoins(root, dest);
		}

		to.groupBy(from.getGroupList());

		to.having(from.getGroupRestriction());

		to.where(from.getRestriction());
	}

	/**
	 */
	@SuppressWarnings("unchecked")
	public static <T> Root<T> findRoot(CriteriaQuery<?> query, Class<T> clazz) {
		for (Root<?> r : query.getRoots()) {
			if (clazz.equals(r.getJavaType())) {
				return (Root<T>) r.as(clazz);
			}

		}

		return (Root<T>) query.getRoots().iterator().next();
	}

	/**
	 */
	public static void copyJoins(From<?, ?> from, From<?, ?> to) {

		for (Join<?, ?> j : from.getJoins()) {
			Join<?, ?> toJoin = to.join(j.getAttribute().getName(), j.getJoinType());

			toJoin.alias(getOrCreateAlias(j));

			copyJoins(j, toJoin);

			for (Fetch<?, ?> f : from.getFetches()) {
				Fetch<?, ?> toFetch = to.fetch(f.getAttribute().getName());
				copyFetches(f, toFetch);
			}
		}
	}

	/**
	 */
	public static void copyFetches(Fetch<?, ?> from, Fetch<?, ?> to) {

		for (Fetch<?, ?> f : from.getFetches()) {

			Fetch<?, ?> toFetch = to.fetch(f.getAttribute().getName());

			copyFetches(f, toFetch);
		}
	}

	/**
	 */
	public static synchronized <T> String getOrCreateAlias(Selection<T> selection) {
		if (aliasCount > 1000)
			aliasCount = 0;

		String alias = selection.getAlias();
		if (alias == null) {
			alias = "JDAL_generatedAlias" + aliasCount++;
			selection.alias(alias);
		}
		return alias;
	}
}