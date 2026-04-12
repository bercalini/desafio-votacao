package com.br.desafio_votacao.DTO;

public record VotoDTO(Long id,
                      String cpf,
                      String opcaoVoto,
                      VotoPautaDTO pauta) {
}
