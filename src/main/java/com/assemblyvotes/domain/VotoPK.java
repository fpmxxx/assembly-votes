package com.assemblyvotes.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Classe de mapeamento dos campos de chave primaria que serao armazenados na tabela voto
 */
@Embeddable
public class VotoPK implements Serializable {

	private static final long serialVersionUID = -2327888626606529486L;

	@Column(name = "CPF")
	private String cpf;

	@ManyToOne
	@JoinColumn(name = "pauta_id", nullable = false)
	private Pauta pauta;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Pauta getPauta() {
		return pauta;
	}

	public void setPauta(Pauta pauta) {
		this.pauta = pauta;
	}

}
