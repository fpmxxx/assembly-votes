package com.assemblyvotes.component;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;

import com.assemblyvotes.exceptions.CPFInvalidException;

/**
 * Testes de validacao de CPF
 */
@ExtendWith(MockitoExtension.class)
class ValidateCPFTest {
	
	@Mock
	private MessageSource messageSource;
	
	@InjectMocks
	private ValidateCPF validateCPF;

	@Test
	void testIsCPFValid() {
		assertTrue(validateCPF.isCPF("91730363075"));
	}
	
	@Test
	void testIsCPFInvalid() {
		Mockito.when(messageSource.getMessage("msg.cpf.invalid", null, null)).thenReturn("CPF inválido");
		
		CPFInvalidException thrown = Assertions.assertThrows(CPFInvalidException.class, () -> {
			validateCPF.isCPF("00000000000");
		});
		
		assertEquals("CPF inválido", thrown.getMessage());
	}

}
