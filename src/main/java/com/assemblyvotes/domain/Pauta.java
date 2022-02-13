package com.assemblyvotes.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Classe de mapeamento dos campos que serao armazenados na tabela pauta
 */
@Entity
@Table(name = "PAUTA")
public class Pauta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NOME", nullable = false)
	private String nome;

	@Column(name = "DATA_INICIO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInicio;

	@Column(name = "MINUTOS_VOTACAO")
	private Integer minutosVotacao;

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

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Integer getMinutosVotacao() {
		return minutosVotacao;
	}

	public void setMinutosVotacao(Integer minutosVotacao) {
		this.minutosVotacao = minutosVotacao;
	}

	@Override
	public String toString() {
		return "Pauta [id=" + id + ", nome=" + nome + ", dataInicio=" + dataInicio + ", minutosVotacao="
				+ minutosVotacao + "]";
	}

}
