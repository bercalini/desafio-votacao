package com.br.desafio_votacao.controller;

import com.br.desafio_votacao.DTO.VotoDTO;
import com.br.desafio_votacao.input.VotoInput;
import com.br.desafio_votacao.service.VotoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/votos")
@Tag(name = "Votos", description = "API responsável pelo registro de votos dos associados")
public class VotoController {

    private final VotoService votoService;

    public VotoController(final VotoService votoService) {
        this.votoService = votoService;
    }

    @PostMapping
    @Operation(summary = "Realizar votação",
               description = "Permite que um associado vote em uma pauta aberta. Cada CPF pode votar apenas uma vez por pauta."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Voto realizado com sucesso",
                    content = @Content(schema = @Schema(implementation = VotoDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou regra de negócio violada"),
            @ApiResponse(responseCode = "404", description = "CPF inválido ou sessão/pauta não encontrada"),
            @ApiResponse(responseCode = "409", description = "Associado já votou na pauta"),
            @ApiResponse(responseCode = "422", description = "CPF não habilitado para votar")
    })
    public ResponseEntity<VotoDTO> votar(@Valid @RequestBody VotoInput votoInput) {
        var votoDTO = votoService.votar(votoInput);

        return ResponseEntity.ok(votoDTO);
    }
}
