package com.assemblyvotes.exceptions;

/**
 * Classe reponsavel por mapear os campos de erro das requisicoes rest
 */
public class ErrorMessage {

	private int statusCode;
	private String message;

	public ErrorMessage(int statusCode, String message) {
		super();
		this.statusCode = statusCode;
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
