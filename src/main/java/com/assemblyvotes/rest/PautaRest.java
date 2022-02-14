package com.assemblyvotes.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assemblyvotes.dto.PautaListResponseDTO;
import com.assemblyvotes.dto.PautaRequestDTO;
import com.assemblyvotes.dto.PautaResponseDTO;
import com.assemblyvotes.service.PautaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Classe que recebe as requisicoes rest das pautas
 */
@Api
@RestController
@RequestMapping("/v1/pauta")
public class PautaRest {

	@Autowired
	private PautaService pautaService;

	@ApiOperation(value = "Salvar pauta")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Pauta salva com sucesso"),
			@ApiResponse(code = 500, message = "Erro ao salvar pauta"), })
	@PostMapping("/save")
	public ResponseEntity<PautaResponseDTO> save(@RequestBody @Valid PautaRequestDTO pautaRequestDTO) 
			throws Exception {
		PautaResponseDTO pautaResponseDTO = pautaService.save(pautaRequestDTO);
		return new ResponseEntity<PautaResponseDTO>(pautaResponseDTO, HttpStatus.OK);
	}

	@ApiOperation(value = "Lista de pautas criadas")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Retorna lista de pautas criadas"),
			@ApiResponse(code = 500, message = "Erro ao consular pauta"), })
	@GetMapping("/list")
	public ResponseEntity<PautaListResponseDTO> getStave(@RequestParam Integer page, @RequestParam Integer size)
			throws Exception {
		PautaListResponseDTO dto = pautaService.listPauta(page, size);
		return new ResponseEntity<PautaListResponseDTO>(dto, HttpStatus.OK);
	}
}
