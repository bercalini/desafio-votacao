package com.br.desafio_votacao.controller;

import com.br.desafio_votacao.DTO.PautaDTO;
import com.br.desafio_votacao.input.PautaInput;
import com.br.desafio_votacao.service.PautaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/v1/pautas")
@Tag(name = "Pautas", description = "API responsável pelo gerenciamento de pautas de votação")
public class PautaController {

    private PautaService pautaService;

    public PautaController(final PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar nova pauta",
               description = "Cria uma nova pauta para ser utilizada em sessões de votação"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pauta criada com sucesso",
                    content = @Content(schema = @Schema(implementation = PautaDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<PautaDTO> criar(@Valid @RequestBody PautaInput pautaInput) {
        var pautaDTO = pautaService.criar(pautaInput);

        return ResponseEntity.status(CREATED).body(pautaDTO);
    }

    @GetMapping("/buscar/{pautaId}")
    @Operation(summary = "Buscar pauta por ID",
               description = "Retorna os dados de uma pauta específica pelo seu ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pauta encontrada",
                    content = @Content(schema = @Schema(implementation = PautaDTO.class))),
            @ApiResponse(responseCode = "404", description = "Pauta não encontrada")
    })
    public ResponseEntity<PautaDTO> buscarPorId(@PathVariable(name = "pautaId")Long pautaId) {
        var pautaDTO = pautaService.buscarPorId(pautaId);

        return ResponseEntity.ok(pautaDTO);
    }
}
