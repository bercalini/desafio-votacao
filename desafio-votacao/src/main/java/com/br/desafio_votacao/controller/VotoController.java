package com.br.desafio_votacao.controller;

import com.br.desafio_votacao.DTO.VotoDTO;
import com.br.desafio_votacao.input.VotoInput;
import com.br.desafio_votacao.service.VotoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/votos")
public class VotoController {

    private final VotoService votoService;

    public VotoController(final VotoService votoService) {
        this.votoService = votoService;
    }

    @PostMapping
    public ResponseEntity<VotoDTO> votar(@Valid @RequestBody VotoInput votoInput) {
        var votoDTO = votoService.votar(votoInput);

        return ResponseEntity.ok(votoDTO);
    }
}
