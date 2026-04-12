package com.br.desafio_votacao.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record SessaoVotacaoDTO(Long id,
                               @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
                               LocalDateTime dataInicio,
                               @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
                               LocalDateTime dataFim,
                               SessaoVotacaoPautaDTO pauta) {
}
