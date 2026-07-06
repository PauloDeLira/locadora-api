package com.lira.dev.locadora_veiculos.dto.request;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AtualizarVeiculoDTO {
    @NotBlank(message = "A cor do veículo é obrigatória para a atualização.")
    @Size(min = 5, max = 30, message = "Cor deve ter entre 5 e 30 caracteres.")
    private String cor;

    @NotNull(message = "O valor da diária é obrigatório.")
    @DecimalMin(value = "0.01", message = "Valor da diária do veículo não pode ser negativa.")
    private BigDecimal valorDiaria;

    @NotNull(message = "O status de disponibilidade é obrigatório.")
    private Boolean disponivel;

}
