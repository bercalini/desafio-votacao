package com.br.desafio_votacao.service;

import com.br.desafio_votacao.enums.OpcaoVoto;
import com.br.desafio_votacao.repository.VotoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResultadoServiceTest {

    @InjectMocks
    private ResultadoService resultadoService;

    @Mock
    private VotoRepository votoRepository;

    @Test
    @DisplayName("Deve testar o resultado")
    void deve_testar_resultado() {
        when(votoRepository.countByPautaIdAndOpcaoVoto(1L, OpcaoVoto.SIM)).thenReturn(10L);

        var resultado = resultadoService.resultado(1L);

        assertNotNull(resultado);
        verify(votoRepository).countByPautaIdAndOpcaoVoto(1L, OpcaoVoto.SIM);
    }

}