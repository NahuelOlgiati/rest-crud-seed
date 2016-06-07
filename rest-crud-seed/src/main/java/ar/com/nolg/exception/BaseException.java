package ar.com.nolg.exception;

import java.util.Arrays;
import java.util.List;

import ar.com.nolg.util.CharUtil;
import ar.com.nolg.util.CompareUtil;

@SuppressWarnings("serial")
public abstract class BaseException extends Exception {
	private List<String> messages;

	/**
	 */
	protected BaseException(List<String> messages) {
		this.messages = messages;
	}

	/**
	 */
	protected BaseException(String message) {
		this.messages = Arrays.asList(message);
	}

	/**
	 */
	public final List<String> getMessages() {
		return messages;
	}

	/**
	 */
	public final String getMessagesAsPlainText() {
		final StringBuilder sb = new StringBuilder();

		for (final String message : getMessages()) {
			if (!CompareUtil.isEmpty(sb.toString())) {
				sb.append(CharUtil.getLineSeparator());
			}
			sb.append(message);
		}
		return sb.toString();
	}
}