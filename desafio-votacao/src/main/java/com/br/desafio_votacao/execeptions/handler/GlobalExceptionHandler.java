package com.br.desafio_votacao.execeptions.handler;

import com.br.desafio_votacao.execeptions.BusinessException;
import com.br.desafio_votacao.execeptions.PautaNaoEncontradaException;
import com.br.desafio_votacao.execeptions.SessaoAbertaException;
import com.br.desafio_votacao.execeptions.SessaoNaoEncontradaException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> handleBusiness(BusinessException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(PautaNaoEncontradaException.class)
    public ResponseEntity<String> pautaNaoEncontrada(PautaNaoEncontradaException ex) {
        return ResponseEntity.status(NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(SessaoNaoEncontradaException.class)
    public ResponseEntity<String> sessaoNaoEncontrada(SessaoNaoEncontradaException ex) {
        return ResponseEntity.status(NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(SessaoAbertaException.class)
    public ResponseEntity<String> sessaoAberta(SessaoAbertaException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
