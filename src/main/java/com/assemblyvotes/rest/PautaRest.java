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

import com.assemblyvotes.dto.PautaDTO;
import com.assemblyvotes.dto.PautaListDTO;
import com.assemblyvotes.service.PautaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Classe que recebe as requisicoes rest
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
	public ResponseEntity<PautaDTO> save(@RequestBody @Valid PautaDTO pautaDTO) 
			throws Exception {
		pautaDTO = pautaService.save(pautaDTO);
		return new ResponseEntity<PautaDTO>(pautaDTO, HttpStatus.OK);
	}

	@ApiOperation(value = "Lista de pautas criadas")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Retorna lista de pautas criadas"),
			@ApiResponse(code = 500, message = "Erro ao consular pauta"), })
	@GetMapping("/list")
	public ResponseEntity<PautaListDTO> getStave(@RequestParam Integer page, @RequestParam Integer size)
			throws Exception {
		PautaListDTO dto = pautaService.listPauta(page, size);
		return new ResponseEntity<PautaListDTO>(dto, HttpStatus.OK);
	}
}
