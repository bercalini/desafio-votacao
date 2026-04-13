package com.br.desafio_votacao.client;

import com.br.desafio_votacao.enums.StatusCpf;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Random;

@Component
public class CpfClientFake {

    public StatusCpf validar(String cpf) {

        if (new Random().nextBoolean()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return new Random().nextBoolean() ? StatusCpf.ABLE_TO_VOTE : StatusCpf.UNABLE_TO_VOTE;
    }
}