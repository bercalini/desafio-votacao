package com.br.desafio_votacao.service;

import com.br.desafio_votacao.DTO.VotoDTO;
import com.br.desafio_votacao.client.CpfClientFake;
import com.br.desafio_votacao.enums.StatusCpf;
import com.br.desafio_votacao.execeptions.BusinessException;
import com.br.desafio_votacao.input.VotoInput;
import com.br.desafio_votacao.mapper.VotoMapper;
import com.br.desafio_votacao.repository.VotoRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
public class VotoService {

    private final VotoRepository votoRepository;
    private final SessaoService sessaoService;
    private final VotoMapper votoMapper;
    private final CpfClientFake cpfClientFake;
    private static final Logger log = LoggerFactory.getLogger(VotoService.class);

    public VotoService(final VotoRepository votoRepository, final SessaoService sessaoService,
                       final CpfClientFake cpfClientFake, final VotoMapper votoMapper) {
        this.votoRepository = votoRepository;
        this.sessaoService = sessaoService;
        this.cpfClientFake = cpfClientFake;
        this.votoMapper = votoMapper;
    }

    @Transactional
    public VotoDTO votar(final VotoInput votoInput) {
        log.info("Iniciando o processo para a votação: {}", votoInput);

        validarCpf(votoInput.getCpf());

        var sessao = sessaoService.buscarPorPauta(votoInput.getPauta().getId());

        if (LocalDateTime.now().isAfter(sessao.getDataFim())) {
            log.warn("Sessão já encerrada para pauta: {}", votoInput.getPauta().getId());
            throw new BusinessException("Sessão encerrada");
        }

        var jaVotou = votoRepository.existsByCpfAndPautaId(
                votoInput.getCpf(),
                votoInput.getPauta().getId()
        );

        if (jaVotou) {
            log.warn("CPF {} já votou na pauta {}", votoInput.getCpf(), votoInput.getPauta().getId());
            throw new BusinessException("Associado já votou nessa pauta");
        }

        var voto = votoMapper.toModel(votoInput);
        voto.setPauta(sessao.getPauta());

        var votoSalvo = votoRepository.save(voto);

        log.info("Voto registrado com sucesso para CPF {} na pauta {}", votoInput.getCpf(), votoInput.getPauta().getId());

        return votoMapper.toDTO(votoSalvo);
    }

    private void validarCpf(String cpf) {
        log.info("Verificando o cpf : {}", cpf);

        try {
            StatusCpf status = cpfClientFake.validar(cpf);

            if (status == StatusCpf.UNABLE_TO_VOTE) {
                throw new BusinessException("CPF não está habilitado para votar");
            }

        } catch (ResponseStatusException ex) {
            throw new BusinessException("CPF inválido");
        }
    }
}
