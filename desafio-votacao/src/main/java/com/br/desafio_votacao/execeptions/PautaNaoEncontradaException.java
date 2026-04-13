package com.br.desafio_votacao.execeptions;

public class PautaNaoEncontradaException extends RuntimeException {

    public PautaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

}
