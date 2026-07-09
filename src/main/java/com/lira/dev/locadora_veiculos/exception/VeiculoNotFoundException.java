package com.lira.dev.locadora_veiculos.exception;

public class VeiculoNotFoundException extends RuntimeException {
    public VeiculoNotFoundException(String message) {
        super(message);
    }
}
