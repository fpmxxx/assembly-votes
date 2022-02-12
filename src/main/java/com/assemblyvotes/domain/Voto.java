package com.assemblyvotes.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Classe de mapeamento dos campos que serao armazenados na tabela voto
 */
@Entity
@Table(name = "VOTO")
public class Voto {

	@EmbeddedId
	private VotoPK id = new VotoPK();

	@Column(nullable = false)
	private Boolean voto;

	public VotoPK getId() {
		return id;
	}

	public void setId(VotoPK id) {
		this.id = id;
	}

	public Boolean getVoto() {
		return voto;
	}

	public void setVoto(Boolean voto) {
		this.voto = voto;
	}

}
