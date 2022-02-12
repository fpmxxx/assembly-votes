package com.assemblyvotes.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Classe responsavel pelo mapeamento dos campos retornados da lista de votos
 */
public class VotoListResponseDTO implements Serializable {

	private static final long serialVersionUID = -409724064441342348L;

	private List<VotoDTO> listVotoDTO;

	private Integer totalPage;

	public List<VotoDTO> getListVotoDTO() {
		return listVotoDTO;
	}

	public void setListVotoDTO(List<VotoDTO> listVotoDTO) {
		this.listVotoDTO = listVotoDTO;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

}
