package com.br.desafio_votacao.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record PautaDTO(Long id,
                       String titulo,
                       @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
                       LocalDateTime dataCadastro) {
}
