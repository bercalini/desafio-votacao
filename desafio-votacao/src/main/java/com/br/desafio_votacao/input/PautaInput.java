package com.br.desafio_votacao.input;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PautaInput {

    @NotBlank(message = "O campo titulo da pauta, não pode ser em branco")
    private String titulo;

}
