package com.br.desafio_votacao.service;

import com.br.desafio_votacao.DTO.SessaoVotacaoDTO;
import com.br.desafio_votacao.execeptions.PautaNaoEncontradaException;
import com.br.desafio_votacao.execeptions.SessaoAbertaException;
import com.br.desafio_votacao.execeptions.SessaoNaoEncontradaException;
import com.br.desafio_votacao.mapper.SessaoVotacaoMapper;
import com.br.desafio_votacao.model.Pauta;
import com.br.desafio_votacao.model.SessaoVotacao;
import com.br.desafio_votacao.repository.PautaRepository;
import com.br.desafio_votacao.repository.SessaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.br.desafio_votacao.config.Constantes.*;
import static java.lang.String.format;

@Service
public class SessaoService {

    private SessaoRepository sessaoRepository;
    private PautaRepository pautaRepository;
    private SessaoVotacaoMapper sessaoVotacaoMapper;
        private static final Logger log = LoggerFactory.getLogger(SessaoService.class);

    public SessaoService(final SessaoRepository sessaoRepository, final PautaRepository pautaRepository,
                         final SessaoVotacaoMapper sessaoVotacaoMapper) {
        this.sessaoRepository = sessaoRepository;
        this.pautaRepository = pautaRepository;
        this.sessaoVotacaoMapper = sessaoVotacaoMapper;
    }

    public SessaoVotacaoDTO abirSessao(Long pautaId, Long minutos) {
        log.info("Verificando os requisitos para abrir a sessão.");

        sessaoRepository.findByPautaId(pautaId).ifPresent(s -> {
            throw new SessaoAbertaException(SESSAO_ABERTA);
        });

        var pauta = buscarPorId(pautaId);

        var sessao = new SessaoVotacao();
        sessao.setPauta(pauta);

        var inicio = LocalDateTime.now();
        var tempo = (minutos == null || minutos <= 0) ? 1 : minutos;

        sessao.setDataInicio(inicio);
        sessao.setDataFim(inicio.plusMinutes(tempo));

        return sessaoVotacaoMapper.toDTO(sessaoRepository.save(sessao));
    }

    public SessaoVotacao buscarPorPauta(Long pautaId) {
        return sessaoRepository.findByPautaId(pautaId)
                .orElseThrow(() -> new SessaoNaoEncontradaException(format(SESSAO_NAO_ENCONTRADA, pautaId)));
    }

    private Pauta buscarPorId(Long pautaId) {
        log.info("Iniciando a busca pela pauta com o id: {}", pautaId);

        return pautaRepository
                .findById(pautaId)
                .orElseThrow(() -> new PautaNaoEncontradaException(format(PAUTA_NAO_ENCONTRADA, pautaId)));
    }
}
