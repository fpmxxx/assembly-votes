package com.assemblyvotes.component;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Testes de validacao de CPF
 */
@SpringBootTest
class ValidateCPFTest {
	
	@Autowired
	private ValidateCPF validateCPF;

	@Test
	void testIsCPFValid() {
		assertTrue(validateCPF.isCPF("91730363075"));
	}
	
	@Test
	void testIsCPFInvalid() {
		assertFalse(validateCPF.isCPF("00000000000"));
	}

}
