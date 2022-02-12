package com.assemblyvotes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assemblyvotes.domain.Pauta;

/**
 * Interface para operacoes de banco de dados com a tabela pauta 
 */
@Repository
public interface PautaRepository extends CrudRepository<Pauta, Long> {
	

}
