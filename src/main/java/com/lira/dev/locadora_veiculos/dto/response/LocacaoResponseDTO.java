package com.lira.dev.locadora_veiculos.dto.response;


import com.lira.dev.locadora_veiculos.enums.StatusLocacao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class LocacaoResponseDTO {

    private Long id;
    private LocalDate dataRetirada;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucao;
    private BigDecimal valorTotal;
    private StatusLocacao status;
    private Long clienteId;
    private String nomeCliente;
    private Long veiculoId;
    private String modeloVeiculo;
    private String placaVeiculo;

}
