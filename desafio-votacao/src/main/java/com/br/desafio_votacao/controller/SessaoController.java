package com.br.desafio_votacao.controller;

import com.br.desafio_votacao.DTO.SessaoVotacaoDTO;
import com.br.desafio_votacao.service.SessaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/sessoes")
public class SessaoController {

    private final SessaoService sessaoService;

    public SessaoController(final SessaoService sessaoService) {
        this.sessaoService = sessaoService;
    }

    @PostMapping("/{pautaId}/pauta")
    public ResponseEntity<SessaoVotacaoDTO> abrirSessao(@PathVariable(name = "pautaId") Long pautaId,
                                                        @RequestParam(required = false) Long minutos) {
        var sessaoVotacaoDTO = sessaoService.abirSessao(pautaId, minutos);

        return ResponseEntity.ok(sessaoVotacaoDTO);
    }
}
