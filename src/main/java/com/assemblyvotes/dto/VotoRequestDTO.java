package com.assemblyvotes.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

//import org.hibernate.validator.constraints.br.CPF;

/**
 * Classe responsavel pelo mapeamento dos campos do voto para requisicoes rest
 */
public class VotoRequestDTO implements Serializable {

	private static final long serialVersionUID = -3898707039012756119L;

	@NotNull(message = "{msg.required}")
//	@CPF
	private String cpf;

	@NotNull(message = "{msg.required}")
	private Long pautaId;

	@NotNull(message = "{msg.required}")
	private Boolean voto;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Long getPautaId() {
		return pautaId;
	}

	public void setPautaId(Long pautaId) {
		this.pautaId = pautaId;
	}

	public Boolean getVoto() {
		return voto;
	}

	public void setVoto(Boolean voto) {
		this.voto = voto;
	}

}
