package com.assemblyvotes.exceptions;

/**
 * Classe para tratar cpf invalido
 */
public class CPFInvalidException extends RuntimeException {

	private static final long serialVersionUID = -805048680017318042L;

	public CPFInvalidException(String message, Throwable cause) {
		super(message, cause);
	}

	public CPFInvalidException(String message) {
		super(message);
	}

}
