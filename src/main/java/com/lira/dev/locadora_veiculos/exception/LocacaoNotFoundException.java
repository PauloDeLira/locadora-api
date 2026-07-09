package com.lira.dev.locadora_veiculos.exception;

public class LocacaoNotFoundException extends RuntimeException {
    public LocacaoNotFoundException(String message) {
        super(message);
    }
}
