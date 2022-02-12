package com.assemblyvotes.dto;

import java.io.Serializable;

/**
 * Classe responsavel pelo mapeamento dos campos do voto para requisicoes rest
 */
public class VotoDTO implements Serializable {

	private static final long serialVersionUID = -6944031433577525478L;

	private Long pautaId;

	private String pauta;

	private Boolean voto;

	private Long quantidadeVotos;

	public VotoDTO() {
	}

	public VotoDTO(Long pautaId, String pauta, Boolean voto, Long quantidadeVotos) {
		super();
		this.pautaId = pautaId;
		this.pauta = pauta;
		this.voto = voto;
		this.quantidadeVotos = quantidadeVotos;
	}

	public Long getPautaId() {
		return pautaId;
	}

	public void setPautaId(Long pautaId) {
		this.pautaId = pautaId;
	}

	public String getPauta() {
		return pauta;
	}

	public void setPauta(String pauta) {
		this.pauta = pauta;
	}

	public Boolean getVoto() {
		return voto;
	}

	public void setVoto(Boolean voto) {
		this.voto = voto;
	}

	public Long getQuantidadeVotos() {
		return quantidadeVotos;
	}

	public void setQuantidadeVotos(Long quantidadeVotos) {
		this.quantidadeVotos = quantidadeVotos;
	}

	@Override
	public String toString() {
		return "VotoDTO [pautaId=" + pautaId + ", pauta=" + pauta + ", voto=" + voto + ", quantidadeVotos="
				+ quantidadeVotos + "]";
	}

}
