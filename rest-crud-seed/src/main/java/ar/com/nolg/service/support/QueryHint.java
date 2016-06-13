package ar.com.nolg.service.support;

import java.io.Serializable;

@SuppressWarnings("serial")
public class QueryHint implements Serializable {
	private int firstResult;
	private int maxResults;
	private boolean countResults;

	/** 
	 */
	public QueryHint(int firstResult, int maxResults, boolean countResults) {
		this.firstResult = firstResult;
		this.maxResults = maxResults;
		this.countResults = countResults;
	}

	/**
	 */
	public QueryHint(int firstResult, int maxResults) {
		this(firstResult, maxResults, true);
	}

	/**
	 */
	public int getFirstResult() {
		return firstResult;
	}

	/**
	 */
	public int getMaxResults() {
		return maxResults;
	}

	/**
	 */
	public boolean isCountResults() {
		return countResults;
	}
}