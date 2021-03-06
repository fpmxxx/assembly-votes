package com.assemblyvotes.dto;

import java.io.Serializable;

/**
 * Classe responsavel pelo mapeamento dos campos do voto para requisicoes rest
 */
public class VotoResponseDTO implements Serializable {

	private static final long serialVersionUID = 2153840607564053991L;

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
