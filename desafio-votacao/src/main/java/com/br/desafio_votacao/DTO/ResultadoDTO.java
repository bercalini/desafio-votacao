package com.br.desafio_votacao.DTO;

import io.swagger.v3.oas.annotations.media.Schema;

public record ResultadoDTO(@Schema(description = "Quantidade de votos SIM", example = "10")
                           Long totalSim,
                           @Schema(description = "Quantidade de votos NÃO", example = "5")
                           Long totalNao,
                           @Schema(description = "Resultado final da votação", example = "SIM venceu")
                           String resultado) {
}
