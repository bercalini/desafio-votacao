package com.br.desafio_votacao.service;

import com.br.desafio_votacao.DTO.PautaDTO;
import com.br.desafio_votacao.input.PautaInput;
import com.br.desafio_votacao.mapper.PautaMapper;
import com.br.desafio_votacao.model.Pauta;
import com.br.desafio_votacao.repository.PautaRepository;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest
class PautaServiceTest {

    @InjectMocks
    private PautaService pautaService;

    @Mock
    private PautaRepository pautaRepository;

    @Mock
    private PautaMapper pautaMapper;

    @BeforeEach
    void setup() {
        openMocks(this);
    }

    @Test
    @DisplayName("Deve testar a criação de uma pauta")
    void deve_testar_criacao_de_uma_pauta() {
        var pautaInput = PautaInput.builder().titulo("teste").build();
        var pauta = Pauta.builder().id(1l).titulo("teste").dataCadastro(LocalDateTime.now()).build();
        var pautaDTO = new PautaDTO(1L, "teste", LocalDateTime.now());

        when(pautaMapper.toModel(pautaInput)).thenReturn(pauta);
        when(pautaRepository.save(pauta)).thenReturn(pauta);
        when(pautaMapper.toDTO(pauta)).thenReturn(pautaDTO);

        var resultado = pautaService.criar(pautaInput);

        assertNotNull(resultado);
        assertEquals(1L, resultado.id());
        assertEquals("teste", resultado.titulo());

        verify(pautaMapper).toModel(pautaInput);
        verify(pautaRepository).save(pauta);
        verify(pautaMapper).toDTO(pauta);
    }

    @Test
    @DisplayName("Deve testar buscar pauta pelo id")
    void deve_testar_buscar_pauta_pelo_id() {
        var pauta = Pauta.builder().id(1l).titulo("teste").dataCadastro(LocalDateTime.now()).build();
        var pautaDTO = new PautaDTO(1L, "teste", LocalDateTime.now());

        when(pautaRepository.findById(1L)).thenReturn(Optional.of(pauta));
        when(pautaMapper.toDTO(pauta)).thenReturn(pautaDTO);

        var resultado = pautaService.buscarPorId(1L);
        assertNotNull(resultado);
        assertEquals(1L, resultado.id());
        assertEquals("teste", resultado.titulo());

        verify(pautaRepository).findById(1L);
        verify(pautaMapper).toDTO(pauta);
    }
}