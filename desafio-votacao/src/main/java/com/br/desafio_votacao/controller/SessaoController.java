package com.br.desafio_votacao.controller;

import com.br.desafio_votacao.DTO.SessaoVotacaoDTO;
import com.br.desafio_votacao.service.SessaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/sessoes")
@Tag(name = "Sessões", description = "API responsável pela abertura e controle de sessões de votação")
public class SessaoController {

    private final SessaoService sessaoService;

    public SessaoController(final SessaoService sessaoService) {
        this.sessaoService = sessaoService;
    }

    @PostMapping("/{pautaId}/pauta")
    @Operation(summary = "Abrir sessão de votação",
               description = "Abre uma sessão de votação para uma pauta. Caso o tempo não seja informado, a sessão terá duração padrão de 1 minuto."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sessão aberta com sucesso",
                    content = @Content(schema = @Schema(implementation = SessaoVotacaoDTO.class))),
            @ApiResponse(responseCode = "400", description = "Erro na requisição"),
            @ApiResponse(responseCode = "404", description = "Pauta não encontrada"),
            @ApiResponse(responseCode = "409", description = "Sessão já existente para a pauta")
    })
    public ResponseEntity<SessaoVotacaoDTO> abrirSessao(@Parameter(description = "ID da pauta que será aberta para votação", example = "1")
                                                        @PathVariable(name = "pautaId") Long pautaId,
                                                        @Parameter(
                                                                description = "Tempo de duração da sessão em minutos. Caso não informado, será considerado 1 minuto.",
                                                                example = "5"
                                                        )
                                                        @RequestParam(required = false) Long minutos) {
        var sessaoVotacaoDTO = sessaoService.abirSessao(pautaId, minutos);

        return ResponseEntity.ok(sessaoVotacaoDTO);
    }
}
