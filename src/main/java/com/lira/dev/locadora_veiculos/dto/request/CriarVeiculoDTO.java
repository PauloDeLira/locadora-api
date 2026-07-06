package com.lira.dev.locadora_veiculos.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CriarVeiculoDTO {

    @NotBlank(message = "A marca do veiculo é obrigatório.")
    @Size(min = 5,
    max = 60,
    message = "Marca tem que ter no entre 5 e 60 caracteres.")
    private String marca;

    @NotBlank(message = "O modelo do veiculo é obrigatório.")
    @Size(min = 5,
    max = 60,
    message = "Modelo tem que ter no entre 5 e 60 caracteres.")
    private String modelo;

    @NotNull(message = "Ano de fabricação do veiculo é obrigatório.")
    @Min(value = 1980, message = "O ano do veiculo não pode ser menor que 1980.")
    @Max(value = 2027, message = "O ano do veiculo não pode ser maior que o limite de fabricação atual.")
    private Integer ano;

    @NotBlank(message = "A placa é obrigatória.")
    @Size(min = 7,
    max = 7,
    message = "A placa deve possuir 7 dígitos.")
    private String placa;

    @NotBlank(message = "Cor do veiculo é obrigatória.")
    @Size(min = 5,
    max = 30,
    message = "Cor deve ter entre 5 e 30 caracteres.")
    private String cor;

    @NotNull
    @DecimalMin(value = "00.1", message = "Valor da diaria do veiculo não pode ser negativa.")
    private BigDecimal valorDiaria;


    private boolean disponivel;


}
