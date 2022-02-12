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

import com.assemblyvotes.dto.VotoListResponseDTO;
import com.assemblyvotes.dto.VotoRequestDTO;
import com.assemblyvotes.dto.VotoResponseDTO;
import com.assemblyvotes.service.VotoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api
@RestController
@RequestMapping("/v1/voto")
public class VotoRest {
	
	@Autowired
	private VotoService votoService;
	
	@ApiOperation(value  = "Salvar voto")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Voto salvo com sucesso"),
	    @ApiResponse(code = 500, message = "Erro ao salvar voto"),
	})
	@PostMapping("/save")
	public ResponseEntity<VotoResponseDTO> save(@RequestBody @Valid VotoRequestDTO votoRequestDTO) throws Exception {
		VotoResponseDTO dto = new VotoResponseDTO();
		dto.setMessage(votoService.save(votoRequestDTO));
		return new ResponseEntity<VotoResponseDTO>(dto, HttpStatus.OK);
	}

	@ApiOperation(value  = "Lista de votos por pauta")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Retorna lista de votos por pauta"),
	    @ApiResponse(code = 500, message = "Erro ao consular votos por pauta"),
	})
	@GetMapping("/list")
	public ResponseEntity<VotoListResponseDTO> getVotes(
			@RequestParam Integer page, 
			@RequestParam Integer size) throws Exception {
		VotoListResponseDTO dto = votoService.listVoto(page, size);
		return new ResponseEntity<VotoListResponseDTO>(dto, HttpStatus.OK);
	}
}
