package com.lira.dev.locadora_veiculos.exception.cliente;

public class ClienteNotFoundException extends RuntimeException {
    public ClienteNotFoundException(String message) {
        super(message);
    }
}
