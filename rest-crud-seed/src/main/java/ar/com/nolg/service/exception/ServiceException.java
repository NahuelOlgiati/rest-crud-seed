package ar.com.nolg.service.exception;

import java.util.List;

import ar.com.nolg.exception.BaseException;

@SuppressWarnings("serial")
public class ServiceException extends BaseException {
	/**
	 */
	public ServiceException(List<String> messages) {
		super(messages);
	}

	/**
	 */
	public ServiceException(String message) {
		super(message);
	}
}