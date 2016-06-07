package ar.com.nolg.model.exception;

import java.util.List;

import ar.com.nolg.exception.BaseException;

@SuppressWarnings("serial")
public final class ValidationException extends BaseException {
	/**
	 */
	public ValidationException(List<String> messages) {
		super(messages);
	}

	/**
	 */
	public ValidationException(String message) {
		super(message);
	}
}