package com.asyl.ecommerce.exceptions;

public class ClientOrSellerHasThisSameEntryException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

	public ClientOrSellerHasThisSameEntryException(String profile) {
		super("Seems like this email or cpf is being used by a: " + profile);
	}

	
	public ClientOrSellerHasThisSameEntryException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
