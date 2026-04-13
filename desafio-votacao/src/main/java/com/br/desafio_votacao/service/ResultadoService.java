package com.br.desafio_votacao.service;

import com.br.desafio_votacao.DTO.ResultadoDTO;
import com.br.desafio_votacao.repository.VotoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.br.desafio_votacao.enums.OpcaoVoto.NAO;
import static com.br.desafio_votacao.enums.OpcaoVoto.SIM;

@Service
public class ResultadoService {

    private final VotoRepository votoRepository;
    private static final Logger log = LoggerFactory.getLogger(ResultadoService.class);

    public ResultadoService(final VotoRepository votoRepository) {
        this.votoRepository = votoRepository;
    }

    public ResultadoDTO resultado(Long pautaId) {
        log.info("Iniciando a verificação do resultado da votação da pauta : {}", pautaId);

        var sim = votoRepository.countByPautaIdAndOpcaoVoto(pautaId, SIM);
        var nao = votoRepository.countByPautaIdAndOpcaoVoto(pautaId, NAO);

        var resultado = sim > nao ? "SIM venceu" : nao > sim ? "NAO venceu" : "EMPATE";

        return new ResultadoDTO(sim, nao, resultado);
    }
}
