package com.br.desafio_votacao.mapper;

import com.br.desafio_votacao.DTO.SessaoVotacaoDTO;
import com.br.desafio_votacao.model.SessaoVotacao;
import org.mapstruct.Mapper;

import static com.br.desafio_votacao.config.Constantes.SPRING_FRAMEWORK;

@Mapper(componentModel = SPRING_FRAMEWORK)
public interface SessaoVotacaoMapper {

    SessaoVotacaoDTO toDTO(SessaoVotacao sessaoVotacao);

}
