package com.br.desafio_votacao.service;

import com.br.desafio_votacao.DTO.SessaoVotacaoDTO;
import com.br.desafio_votacao.DTO.SessaoVotacaoPautaDTO;
import com.br.desafio_votacao.mapper.SessaoVotacaoMapper;
import com.br.desafio_votacao.model.Pauta;
import com.br.desafio_votacao.model.SessaoVotacao;
import com.br.desafio_votacao.repository.PautaRepository;
import com.br.desafio_votacao.repository.SessaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest
class SessaoServiceTest {

    @InjectMocks
    private SessaoService sessaoService;

    @Mock
    private SessaoRepository sessaoRepository;

    @Mock
    private SessaoVotacaoMapper sessaoVotacaoMapper;

    @Mock
    private PautaRepository pautaRepository;

    @BeforeEach
    void setup() {
        openMocks(this);
    }

    @Test
    @DisplayName("Deve testar abrir sessao")
    void teste_testar_abrir_sessao() {
        var sessaoVotacao = SessaoVotacao.builder().id(1L).dataInicio(LocalDateTime.now())
                .dataFim(LocalDateTime.now()).pauta(Pauta.builder().id(1L)
                        .dataCadastro(LocalDateTime.now()).titulo("teste").build()).build();

        var pauta = Pauta.builder().id(1L).titulo("teste").dataCadastro(LocalDateTime.now()).build();
        var sessaoVotacaoDTO = new SessaoVotacaoDTO(1L, LocalDateTime.now(), LocalDateTime.now(),
                new SessaoVotacaoPautaDTO(1L));

        when(sessaoRepository.findByPautaId(1L)).thenReturn(Optional.empty());
        when(pautaRepository.findById(1L)).thenReturn(Optional.of(pauta));
        when(sessaoRepository.save(any(SessaoVotacao.class))).thenReturn(sessaoVotacao);
        when(sessaoVotacaoMapper.toDTO(sessaoVotacao)).thenReturn(sessaoVotacaoDTO);

        var resultado = sessaoService.abirSessao(1L, 20L);
        assertNotNull(resultado);
        assertEquals(1L, resultado.id());
        assertEquals(1L, resultado.pauta().id());

        verify(sessaoRepository).save(any(SessaoVotacao.class));
        verify(sessaoRepository).findByPautaId(1L);
        verify(pautaRepository).findById(1L);
        verify(sessaoVotacaoMapper).toDTO(sessaoVotacao);
    }

    @Test
    @DisplayName("Deve testar a busca por pauta id")
    void deve_testar_busca_pauta_id() {
        var sessaoVotacao = SessaoVotacao.builder().id(1L).dataInicio(LocalDateTime.now())
                .dataFim(LocalDateTime.now()).pauta(Pauta.builder().id(1L)
                        .dataCadastro(LocalDateTime.now()).titulo("teste").build()).build();

        when(sessaoRepository.findByPautaId(1L)).thenReturn(Optional.of(sessaoVotacao));

        var resultado = sessaoService.buscarPorPauta(1L);
        assertNotNull(resultado);

        verify(sessaoRepository).findByPautaId(1L);
    }
}