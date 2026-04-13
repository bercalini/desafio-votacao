package com.br.desafio_votacao.controller;

import com.br.desafio_votacao.DTO.VotoDTO;
import com.br.desafio_votacao.DTO.VotoPautaDTO;
import com.br.desafio_votacao.enums.OpcaoVoto;
import com.br.desafio_votacao.input.VotoInput;
import com.br.desafio_votacao.input.VotoPautaInput;
import com.br.desafio_votacao.service.VotoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
class VotoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private VotoService votoService;

    @Test
    @DisplayName("deve testar o controller direto")
    void deve_testar_controller_direto() {
        var votoService = mock(VotoService.class);
        var votoController = new VotoController(votoService);


        var input = VotoInput.builder().opcaoVoto(OpcaoVoto.SIM).cpf("123")
                .pauta(VotoPautaInput.builder().id(1L).build()).build();
        input.setCpf("123");

        when(votoService.votar(any()))
                .thenReturn(new VotoDTO(1L, "123", "1", new VotoPautaDTO(1L)));

        votoController.votar(input);
    }
}