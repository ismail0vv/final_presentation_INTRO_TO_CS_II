package com.asyl.ecommerce.exceptions;

public class DuplicateEntryException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

	public DuplicateEntryException() {
		super("CPF and/or Email is already being used");
	}

	public DuplicateEntryException(String msg) {
		super(msg);
	}

	public DuplicateEntryException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
