package com.assemblyvotes.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.assemblyvotes.domain.Voto;
import com.assemblyvotes.domain.VotoPK;
import com.assemblyvotes.dto.VotoDTO;

/**
 * Interface responsavel por consultas com banco de dados dos votos
 */
@Repository
public interface VotoListRepository extends PagingAndSortingRepository<Voto, VotoPK> {

	@Query("SELECT new com.assemblyvotes.dto.VotoDTO(v.id.pauta.id, v.id.pauta.nome, v.voto, COUNT(v.id.pauta.id)) "
			+ " FROM Voto v "
			+ " GROUP BY v.id.pauta.id, v.id.pauta.nome, v.voto "
			+ " ORDER BY v.id.pauta.nome ")
	Page<VotoDTO> listVotos(Pageable pageable);
}
