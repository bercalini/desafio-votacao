package com.br.desafio_votacao.service;

import com.br.desafio_votacao.DTO.VotoDTO;
import com.br.desafio_votacao.DTO.VotoPautaDTO;
import com.br.desafio_votacao.client.CpfClientFake;
import com.br.desafio_votacao.enums.OpcaoVoto;
import com.br.desafio_votacao.enums.StatusCpf;
import com.br.desafio_votacao.execeptions.BusinessException;
import com.br.desafio_votacao.input.VotoInput;
import com.br.desafio_votacao.input.VotoPautaInput;
import com.br.desafio_votacao.mapper.VotoMapper;
import com.br.desafio_votacao.model.Pauta;
import com.br.desafio_votacao.model.SessaoVotacao;
import com.br.desafio_votacao.model.Voto;
import com.br.desafio_votacao.repository.VotoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VotoServiceTest {

    @InjectMocks
    private VotoService votoService;

    @Mock
    private VotoRepository votoRepository;

    @Mock
    private SessaoService sessaoService;

    @Mock
    private VotoMapper votoMapper;

    @Mock
    private CpfClientFake cpfClientFake;

    @Test
    @DisplayName("Deve votar com sucesso")
    void deve_votar_com_sucesso() {
        var input = VotoInput.builder().cpf("123").opcaoVoto(OpcaoVoto.SIM)
                .pauta(VotoPautaInput.builder().id(1L).build()).build();

        var pauta = Pauta.builder().id(1L).build();

        var sessao = SessaoVotacao.builder().dataFim(LocalDateTime.now().plusMinutes(10))
                .pauta(pauta).build();

        var voto = Voto.builder().build();
        var votoDTO = new VotoDTO(1L, "123", "1", new VotoPautaDTO(1L));

        when(cpfClientFake.validar("123")).thenReturn(StatusCpf.ABLE_TO_VOTE);
        when(sessaoService.buscarPorPauta(1L)).thenReturn(sessao);
        when(votoRepository.existsByCpfAndPautaId("123", 1L)).thenReturn(false);
        when(votoMapper.toModel(input)).thenReturn(voto);
        when(votoRepository.save(voto)).thenReturn(voto);
        when(votoMapper.toDTO(voto)).thenReturn(votoDTO);

        var resultado = votoService.votar(input);

        assertNotNull(resultado);
    }

    @Test
    @DisplayName("Deve lançar erro quando CPF não pode votar")
    void deve_erro_quando_cpf_invalido() {
        var input = VotoInput.builder().cpf("123").build();

        when(cpfClientFake.validar("123")).thenReturn(StatusCpf.UNABLE_TO_VOTE);

        assertThrows(BusinessException.class, () -> votoService.votar(input));
    }

    @Test
    @DisplayName("Deve lançar erro quando sessão encerrada")
    void deve_erro_quando_sessao_encerrada() {
        var input = VotoInput.builder().cpf("123").opcaoVoto(OpcaoVoto.SIM)
                .pauta(VotoPautaInput.builder().id(1L).build()).build();

        var sessao = SessaoVotacao.builder().dataFim(LocalDateTime.now().minusMinutes(1)).build();

        when(cpfClientFake.validar("123")).thenReturn(StatusCpf.ABLE_TO_VOTE);
        when(sessaoService.buscarPorPauta(1L)).thenReturn(sessao);

        assertThrows(BusinessException.class, () -> votoService.votar(input));
    }

    @Test
    @DisplayName("Deve lançar erro quando CPF já votou")
    void deve_erro_quando_ja_votou() {
        var input = VotoInput.builder().cpf("123").opcaoVoto(OpcaoVoto.SIM)
                .pauta(VotoPautaInput.builder().id(1L).build()).build();

        var sessao = SessaoVotacao.builder().dataFim(LocalDateTime.now().plusMinutes(10)).build();

        when(cpfClientFake.validar("123")).thenReturn(StatusCpf.ABLE_TO_VOTE);
        when(sessaoService.buscarPorPauta(1L)).thenReturn(sessao);
        when(votoRepository.existsByCpfAndPautaId("123", 1L)).thenReturn(true);

        assertThrows(BusinessException.class, () -> votoService.votar(input));
    }
}