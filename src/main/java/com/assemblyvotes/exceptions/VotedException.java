package com.assemblyvotes.exceptions;

/**
 * Classe para tratar excecoes de votos repetidos 
 */
public class VotedException extends RuntimeException {

	private static final long serialVersionUID = -4462729999636270962L;

	public VotedException(String message, Throwable cause) {
		super(message, cause);
	}

	public VotedException(String message) {
		super(message);
	}

}
