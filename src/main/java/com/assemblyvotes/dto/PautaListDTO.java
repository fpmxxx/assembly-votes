package com.assemblyvotes.dto;

import java.io.Serializable;
import java.util.List;

import com.assemblyvotes.domain.Pauta;

/**
 * Classe responsavel pelo mapeamento dos campos retornados da lista de pautas cadastradas
 */
public class PautaListDTO implements Serializable {

	private static final long serialVersionUID = 1781278912913571491L;

	private List<Pauta> listPauta;

	private Integer totalPage;

	public List<Pauta> getListPauta() {
		return listPauta;
	}

	public void setListPauta(List<Pauta> listPauta) {
		this.listPauta = listPauta;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

}
