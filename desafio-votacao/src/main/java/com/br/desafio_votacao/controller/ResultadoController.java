package com.br.desafio_votacao.controller;

import com.br.desafio_votacao.DTO.ResultadoDTO;
import com.br.desafio_votacao.service.ResultadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/resultados")
public class ResultadoController {

    private final ResultadoService resultadoService;

    public ResultadoController(final ResultadoService resultadoService) {
        this.resultadoService = resultadoService;
    }

    @GetMapping("/{pautaId}/pauta")
    public ResponseEntity<ResultadoDTO> resultado(@PathVariable(name = "pautaId") Long pautaId) {
        var resultadoDTO = resultadoService.resultado(pautaId);

        return ResponseEntity.ok(resultadoDTO);
    }

}
