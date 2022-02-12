package com.assemblyvotes.exceptions;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * Classe responsavel por formatar e apresentar mensagem de erro das requisicoes rest
 */
@RestControllerAdvice
public class RestExceptionHandler {

	/**
	 * Erros de requisicao em geral
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessage globalExceptionHandler(Exception ex, WebRequest request) {
		
		ErrorMessage message = null;
		
		message = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
		
		return message;
	}
	
	/**
	 * Erros de validacoes de campos
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorMessage validationExceptionHandler(Exception ex, WebRequest request) {
		
		ErrorMessage message = null;
		
    	if (ex instanceof RuntimeException) {
			message = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
		} else {
			message = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
		}
		
		return message;
	}
	
	/**
	 * Erros de validacoes de campos
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
}
