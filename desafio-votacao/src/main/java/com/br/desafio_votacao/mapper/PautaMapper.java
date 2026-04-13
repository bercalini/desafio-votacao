package com.br.desafio_votacao.mapper;

import com.br.desafio_votacao.DTO.PautaDTO;
import com.br.desafio_votacao.input.PautaInput;
import com.br.desafio_votacao.model.Pauta;
import org.mapstruct.Mapper;

import static com.br.desafio_votacao.config.Constantes.SPRING_FRAMEWORK;

@Mapper(componentModel = SPRING_FRAMEWORK)
public interface PautaMapper {

    Pauta toModel(PautaInput pautaInput);

    PautaDTO toDTO(Pauta pauta);

}
