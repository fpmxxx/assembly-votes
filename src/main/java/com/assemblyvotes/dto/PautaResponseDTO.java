package com.assemblyvotes.dto;

import java.io.Serializable;

import com.assemblyvotes.domain.Pauta;

/**
 * Classe responsavel pelo mapeamento dos campos da pauta para requisicoes rest
 */
public class PautaResponseDTO implements Serializable {

	private static final long serialVersionUID = -2200704939382311323L;

	private Long id;

	private String nome;

	public PautaResponseDTO() {
		super();
	}

	public PautaResponseDTO(Pauta pauta) {
		this.id = pauta.getId();
		this.nome = pauta.getNome();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
