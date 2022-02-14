package com.assemblyvotes.service;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assemblyvotes.component.ValidateCPF;
import com.assemblyvotes.domain.Pauta;
import com.assemblyvotes.domain.Voto;
import com.assemblyvotes.domain.VotoPK;
import com.assemblyvotes.dto.VotoDTO;
import com.assemblyvotes.dto.VotoListResponseDTO;
import com.assemblyvotes.dto.VotoRequestDTO;
import com.assemblyvotes.exceptions.TimeoutSessionException;
import com.assemblyvotes.exceptions.VotedException;
import com.assemblyvotes.repository.PautaRepository;
import com.assemblyvotes.repository.PautaSearchRepository;
import com.assemblyvotes.repository.VotoRepository;
import com.assemblyvotes.repository.VotoSearchRepository;

/**
 * Classe que processa a informacoes dos votos
 */
@Service
public class VotoService {

	private Logger LOGGER = LogManager.getLogger(VotoService.class);
	
	@Autowired
	private PautaRepository pautaRepository;
	
	@Autowired
	private PautaSearchRepository pautaSearchRepository;
	
	@Autowired
	private VotoRepository votoRepository;
	
	@Autowired
	private VotoSearchRepository votoSearchRepository;
	
	@Autowired
	private ValidateCPF validateCPF;
	
	@Autowired
	private MessageSource messageSource;

	@Value("${config.time-session}")
	private Long timeSession;
	
	
	/**
	 * Salva voto caso o cpf informado ainda nao tenha votado para a pauta selecionada
	 * e a votacao ainda nao tenha sido encerrada
	 * @param votoRequestDTO
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public String save(VotoRequestDTO votoRequestDTO) throws Exception {
		LOGGER.debug("save - votoRequestDTO: {}", votoRequestDTO.toString());
		
		validateCPF.isCPF(votoRequestDTO.getCpf());
		
		Pauta pauta = pautaSearchRepository.findById(votoRequestDTO.getPautaId()).orElseThrow(() -> 
			new EntityNotFoundException(messageSource.getMessage("msg.pauta.notFound", null, null)));
		
		LOGGER.debug("pauta: {}", pauta.toString());
		
		checkTimeoutSession(pauta);
		
		checkVoted(pauta, votoRequestDTO.getCpf());

		VotoPK votoPK = new VotoPK();
		votoPK.setCpf(votoRequestDTO.getCpf());
		votoPK.setPauta(pauta);
		
		Voto voto = new Voto();
		voto.setId(votoPK);
		voto.setVoto(votoRequestDTO.getVoto());
		
		votoRepository.save(voto);
		
		return messageSource.getMessage("msg.vote.save", null, null); 
	}
	
	/**
	 * Lista de votos por pauta com totais
	 * @param page
	 * @param size
	 */
	public VotoListResponseDTO listVoto(Integer page, Integer size) {
		LOGGER.info("listVoto - page: {}, size: {}", page, size);
		
		Pageable pageable = PageRequest.of(page, size);
		
		Page<VotoDTO> pageVote = votoSearchRepository.listVotos(pageable);
		VotoListResponseDTO dto = new VotoListResponseDTO();
		dto.setListVoto(pageVote.getContent());
		dto.setTotalPage(pageVote.getTotalPages());
		
		return dto;
	}

	/**
	 * Verifica se o tempo limite para voto ainda e valido
	 * 
	 * @param pauta
	 */
	private void checkTimeoutSession(Pauta pauta) {
		Calendar now = Calendar.getInstance();
		
		Long timeSessionOpen = 0L;
		
		if (pauta.getDataInicio() != null) {
			timeSessionOpen = TimeUnit.MILLISECONDS.toMinutes(Math.abs(now.getTimeInMillis() - pauta.getDataInicio().getTime()));
		} else {
			pauta.setDataInicio(now.getTime());
			pautaRepository.save(pauta);
		}

		Long endTimeSession = null;
		if (pauta.getMinutosVotacao() != null) {
			endTimeSession = pauta.getMinutosVotacao().longValue();
		} else {
			endTimeSession = timeSession;
		}

		if (timeSessionOpen > endTimeSession) {
			throw new TimeoutSessionException(messageSource.getMessage("msg.vote.closed", null, null));
		}
	}
	
	/**
	 * Verifica se o usuario ja votou para a pauta
	 * 
	 * @param pauta
	 * @param cpf
	 */
	private void checkVoted(Pauta pauta, String cpf) {
		VotoPK voteId = new VotoPK();
		voteId.setPauta(pauta);
		voteId.setCpf(cpf);
		
		List<Voto> listVoto = votoSearchRepository.findVotoById(voteId);
		
		if (!listVoto.isEmpty()) {
			throw new VotedException(messageSource.getMessage("msg.vote.repeated", null, null));
		}
		
	}

}
