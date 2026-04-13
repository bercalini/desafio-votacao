package com.br.desafio_votacao.service;

import com.br.desafio_votacao.DTO.PautaDTO;
import com.br.desafio_votacao.execeptions.PautaNaoEncontradaException;
import com.br.desafio_votacao.input.PautaInput;
import com.br.desafio_votacao.mapper.PautaMapper;
import com.br.desafio_votacao.repository.PautaRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.br.desafio_votacao.config.Constantes.PAUTA_NAO_ENCONTRADA;
import static java.lang.String.format;

@Service
public class PautaService {

    private final PautaRepository pautaRepository;
    private final PautaMapper pautaMapper;
    private static final Logger log = LoggerFactory.getLogger(PautaService.class);

    public PautaService(final PautaRepository pautaRepository, final PautaMapper pautaMapper) {
        this.pautaRepository = pautaRepository;
        this.pautaMapper = pautaMapper;
    }

    @Transactional
    public PautaDTO criar(final PautaInput pautaInput) {
        log.info("Iniciando criação de pauta: {}", pautaInput);

        var pauta = pautaMapper.toModel(pautaInput);

        var pautaSalva = pautaRepository.save(pauta);

        log.info("Pauta criada com sucesso. ID: {}", pautaSalva.getId());

        return pautaMapper.toDTO(pautaSalva);
    }

    public PautaDTO buscarPorId(Long pautaId) {
        log.info("Iniciando a buscar pela pauta pelo id: {}", pautaId);

        return pautaRepository
                .findById(pautaId)
                .map(pautaMapper::toDTO)
                .orElseThrow(() -> new PautaNaoEncontradaException(format(PAUTA_NAO_ENCONTRADA, pautaId)));

    }
}
