package com.br.desafio_votacao.service;

import com.br.desafio_votacao.enums.OpcaoVoto;
import com.br.desafio_votacao.repository.VotoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest
class ResultadoServiceTest {

    @InjectMocks
    private ResultadoService resultadoService;

    @Mock
    private VotoRepository votoRepository;

    @BeforeEach
    void setup() {
        openMocks(this);
    }

    @Test
    @DisplayName("Deve testar o resultado")
    void deve_testar_resultado() {
        when(votoRepository.countByPautaIdAndOpcaoVoto(1L, OpcaoVoto.SIM)).thenReturn(10L);

        var resultado = resultadoService.resultado(1L);

        assertNotNull(resultado);
        verify(votoRepository).countByPautaIdAndOpcaoVoto(1L, OpcaoVoto.SIM);
    }

}