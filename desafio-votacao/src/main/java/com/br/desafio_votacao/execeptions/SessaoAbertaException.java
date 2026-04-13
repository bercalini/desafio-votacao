package com.br.desafio_votacao.execeptions;

public class SessaoAbertaException extends RuntimeException {

    public SessaoAbertaException(String mensagem) {
        super(mensagem);
    }

}
