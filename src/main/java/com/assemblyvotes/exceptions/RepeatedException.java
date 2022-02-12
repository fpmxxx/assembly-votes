package com.assemblyvotes.exceptions;

/**
 * Classe responsavel pelo tratamento de cadastros repetidos 
 */
public class RepeatedException extends RuntimeException {

	private static final long serialVersionUID = -5149364211480904682L;

	public RepeatedException(String message, Throwable cause) {
		super(message, cause);
	}

	public RepeatedException(String message) {
		super(message);
	}
}
