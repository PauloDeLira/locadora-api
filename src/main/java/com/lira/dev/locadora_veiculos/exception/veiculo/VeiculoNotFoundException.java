package com.lira.dev.locadora_veiculos.exception.veiculo;

public class VeiculoNotFoundException extends RuntimeException {
    public VeiculoNotFoundException(String message) {
        super(message);
    }
}
