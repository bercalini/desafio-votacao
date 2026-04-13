package com.br.desafio_votacao.mapper;

import com.br.desafio_votacao.DTO.VotoDTO;
import com.br.desafio_votacao.input.VotoInput;
import com.br.desafio_votacao.model.Voto;
import org.mapstruct.Mapper;

import static com.br.desafio_votacao.config.Constantes.SPRING_FRAMEWORK;

@Mapper(componentModel = SPRING_FRAMEWORK)
public interface VotoMapper {

    Voto toModel(VotoInput votoInput);

    VotoDTO toDTO(Voto voto);

}
