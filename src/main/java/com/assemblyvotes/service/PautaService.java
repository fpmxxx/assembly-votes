package com.assemblyvotes.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assemblyvotes.domain.Pauta;
import com.assemblyvotes.dto.PautaListResponseDTO;
import com.assemblyvotes.dto.PautaRequestDTO;
import com.assemblyvotes.dto.PautaResponseDTO;
import com.assemblyvotes.exceptions.RepeatedException;
import com.assemblyvotes.repository.PautaRepository;
import com.assemblyvotes.repository.PautaSearchRepository;

/**
 *  Classe que processa a informacoes das pautas
 */
@Service
public class PautaService {
	
	private Logger LOGGER = LogManager.getLogger(PautaService.class);
	
	@Autowired
	private PautaRepository pautaRepository;
	
	@Autowired
	private PautaSearchRepository pautaSearchRepository;
	
	@Autowired
	private MessageSource messageSource;
	
	/**
	 * Salva uma nova pauta caso nao exista
	 * @param pautaRequestDTO
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public PautaResponseDTO save(PautaRequestDTO pautaRequestDTO) throws Exception {
		LOGGER.info("save: {}", pautaRequestDTO.toString());
		
		List<Pauta> listPauta = pautaSearchRepository.findPauta(pautaRequestDTO.getNome().trim());
		
		if (listPauta.isEmpty()) {
			Pauta pauta = new Pauta();
			pauta.setNome(pautaRequestDTO.getNome());
			pauta.setMinutosVotacao(pautaRequestDTO.getMinutosVotacao());
			
			pauta = pautaRepository.save(pauta);
			return new PautaResponseDTO(pauta);
		} else {
			throw new RepeatedException(messageSource.getMessage("msg.repeated", null, null));
		}
	}

	/**
	 * Lista de pautas cadastradas
	 * @param page
	 * @param size
	 * @return
	 */
	@Transactional(readOnly = true)
	public PautaListResponseDTO listPauta(Integer page, Integer size) {
		LOGGER.info("listPauta - page: {}, size: {}", page, size);
		
		Pageable pageable = PageRequest.of(page, size);
		
		Page<Pauta> pagePauta = pautaSearchRepository.findAll(pageable);
		
		PautaListResponseDTO dto = new PautaListResponseDTO();
		dto.setListPauta(pagePauta.getContent());
		dto.setTotalPage(pagePauta.getTotalPages());
		
		return dto;
	}
}
