package com.br.desafio_votacao.controller;

import com.br.desafio_votacao.DTO.ResultadoDTO;
import com.br.desafio_votacao.service.ResultadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/resultados")
@Tag(name = "Resultados", description = "API responsável pela apuração de votos das pautas")
public class ResultadoController {

    private final ResultadoService resultadoService;

    public ResultadoController(final ResultadoService resultadoService) {
        this.resultadoService = resultadoService;
    }

    @GetMapping("/{pautaId}/pauta")
    @Operation(summary = "Obter resultado da votação",
               description = "Realiza a contagem dos votos (SIM/NÃO) de uma pauta e retorna o resultado final"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resultado calculado com sucesso",
                    content = @Content(schema = @Schema(implementation = ResultadoDTO.class))),
            @ApiResponse(responseCode = "404", description = "Pauta não encontrada"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição")
    })
    public ResponseEntity<ResultadoDTO> resultado(@PathVariable(name = "pautaId") Long pautaId) {
        var resultadoDTO = resultadoService.resultado(pautaId);

        return ResponseEntity.ok(resultadoDTO);
    }
}
