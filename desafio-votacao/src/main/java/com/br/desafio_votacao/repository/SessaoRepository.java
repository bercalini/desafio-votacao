package com.br.desafio_votacao.repository;

import com.br.desafio_votacao.model.SessaoVotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessaoRepository extends JpaRepository<SessaoVotacao, Long> {

    Optional<SessaoVotacao> findByPautaId(Long pautaId);

}
