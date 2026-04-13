package com.br.desafio_votacao.controller;

import com.br.desafio_votacao.DTO.SessaoVotacaoDTO;
import com.br.desafio_votacao.DTO.SessaoVotacaoPautaDTO;
import com.br.desafio_votacao.service.SessaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class SessaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deve_testar_controller_sem_spring() {
        var service = mock(SessaoService.class);
        var controller = new SessaoController(service);

        var dto = new SessaoVotacaoDTO(1L, LocalDateTime.now(), LocalDateTime.now(),
                new SessaoVotacaoPautaDTO(1L));

        when(service.abirSessao(1L, null))
                .thenReturn(dto);

        controller.abrirSessao(1L, null);
    }
}