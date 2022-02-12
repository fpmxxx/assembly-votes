package com.assemblyvotes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assemblyvotes.domain.Voto;
import com.assemblyvotes.domain.VotoPK;

/**
 * Interface responsavel por operacoes com banco de dados dos votos 
 */
@Repository
public interface VotoRepository extends CrudRepository<Voto, VotoPK> {

}
