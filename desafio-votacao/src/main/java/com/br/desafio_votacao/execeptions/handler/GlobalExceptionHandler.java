package com.br.desafio_votacao.execeptions.handler;

import com.br.desafio_votacao.execeptions.BusinessException;
import com.br.desafio_votacao.execeptions.PautaNaoEncontradaException;
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

}
