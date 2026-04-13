package com.br.desafio_votacao.execeptions;

public class SessaoNaoEncontradaException extends RuntimeException {

    public SessaoNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

}
