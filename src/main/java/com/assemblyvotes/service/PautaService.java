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
import com.assemblyvotes.dto.PautaDTO;
import com.assemblyvotes.dto.PautaListDTO;
import com.assemblyvotes.exceptions.RepeatedException;
import com.assemblyvotes.repository.PautaListRepository;
import com.assemblyvotes.repository.PautaRepository;

/**
 *  Classe que processa a informacoes das pautas
 */
@Service
public class PautaService {
	
	private Logger LOGGER = LogManager.getLogger(PautaService.class);
	
	@Autowired
	private PautaRepository pautaRepository;
	
	@Autowired
	private PautaListRepository pautaListRepository;
	
	@Autowired
	private MessageSource messageSource;
	
	/**
	 * Salva uma nova pauta caso nao exista
	 * @param pautaDTO
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public PautaDTO save(PautaDTO pautaDTO) throws Exception {
		LOGGER.info("save: {}", pautaDTO.toString());
		
		List<Pauta> listPauta = pautaListRepository.findPauta(pautaDTO.getNome().trim());
		
		if (listPauta.isEmpty()) {
			Pauta pauta = new Pauta();
			pauta.setNome(pautaDTO.getNome());
			pauta.setMinutosVotacao(pautaDTO.getMinutosVotacao());
			
			pauta = pautaRepository.save(pauta);
			return new PautaDTO(pauta);
		} else {
			throw new RepeatedException(messageSource.getMessage("msg.repeated", null, null));
		}
	}

	@Transactional(readOnly = true)
	public PautaListDTO listPauta(Integer page, Integer size) {
		LOGGER.info("listPauta - page: {}, size: {}", page, size);
		
		Pageable pageable = PageRequest.of(page, size);
		
		Page<Pauta> pagePauta = pautaListRepository.findAll(pageable);
		
		PautaListDTO dto = new PautaListDTO();
		dto.setListPauta(pagePauta.getContent());
		dto.setTotalPage(pagePauta.getTotalPages());
		
		return dto;
	}
}
