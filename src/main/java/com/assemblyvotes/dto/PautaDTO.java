package com.assemblyvotes.dto;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.assemblyvotes.domain.Pauta;

/**
 * Classe responsavel pelo mapeamento dos campos da pauta para requisicoes rest
 */
public class PautaDTO implements Serializable {

	private static final long serialVersionUID = -2200704939382311323L;

	private Long id;

	@NotNull(message = "{msg.required}")
	@Size(message = "{msg.size}", min = 3, max = 50)
	private String nome;

	@Min(message = "{msg.min}", value = 1)
	@Max(message = "{msg.max}", value = 60)
	private Integer minutosVotacao;

	public PautaDTO() {
		super();
	}

	public PautaDTO(Pauta pauta) {
		this.id = pauta.getId();
		this.nome = pauta.getNome();
		this.minutosVotacao = pauta.getMinutosVotacao();
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

	public Integer getMinutosVotacao() {
		return minutosVotacao;
	}

	public void setMinutosVotacao(Integer minutosVotacao) {
		this.minutosVotacao = minutosVotacao;
	}

	@Override
	public String toString() {
		return "PautaDTO [id=" + id + ", nome=" + nome + ", minutosVotacao=" + minutosVotacao + "]";
	}

}
