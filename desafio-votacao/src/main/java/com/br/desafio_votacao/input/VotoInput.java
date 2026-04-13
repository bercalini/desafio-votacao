package com.br.desafio_votacao.input;

import com.br.desafio_votacao.enums.OpcaoVoto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class VotoInput {

    @NotBlank(message = "O campo cpf no voto não pode ser em branco.")
    private String cpf;

    private OpcaoVoto opcaoVoto;

    private VotoPautaInput pauta;

}
