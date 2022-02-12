package com.assemblyvotes.exceptions;

/**
 * Classe para tratar excecoes de tempo de votacao
 */
public class TimeoutSessionException extends RuntimeException {

	private static final long serialVersionUID = -4462729999636270962L;

	public TimeoutSessionException(String message, Throwable cause) {
		super(message, cause);
	}

	public TimeoutSessionException(String message) {
		super(message);
	}

}
