package com.br.desafio_votacao.controller;

import com.br.desafio_votacao.DTO.PautaDTO;
import com.br.desafio_votacao.input.PautaInput;
import com.br.desafio_votacao.service.PautaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/v1/pautas")
public class PautaController {

    private PautaService pautaService;

    public PautaController(final PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @PostMapping("/criar")
    public ResponseEntity<PautaDTO> criar(@Valid @RequestBody PautaInput pautaInput) {
        var pautaDTO = pautaService.criar(pautaInput);

        return ResponseEntity.status(CREATED).body(pautaDTO);
    }

    @GetMapping("/buscar/{pautaId}")
    public ResponseEntity<PautaDTO> buscarPorId(@PathVariable(name = "pautaId")Long pautaId) {
        var pautaDTO = pautaService.buscarPorId(pautaId);

        return ResponseEntity.ok(pautaDTO);
    }

}
