package com.assemblyvotes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.assemblyvotes.domain.Pauta;

/**
 * Interface para consultas no banco de dados com a tabela pauta
 */
@Repository
public interface PautaSearchRepository extends PagingAndSortingRepository<Pauta, Long> {

	@Query("SELECT p FROM Pauta p WHERE UPPER(p.nome) LIKE CONCAT(UPPER(:nome))")
	List<Pauta> findPauta(@Param("nome") String nome);

}
