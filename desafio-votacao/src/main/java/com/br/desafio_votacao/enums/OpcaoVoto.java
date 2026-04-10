package com.br.desafio_votacao.enums;

public enum OpcaoVoto {
    SIM(1),
    NAO(2);

    private Integer valor;

    OpcaoVoto(Integer valor) {
        this.valor = valor;
    }

}
