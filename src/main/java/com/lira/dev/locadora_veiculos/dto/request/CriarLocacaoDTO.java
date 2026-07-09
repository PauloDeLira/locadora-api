package com.lira.dev.locadora_veiculos.dto.request;

import com.lira.dev.locadora_veiculos.enums.StatusLocacao;
import jakarta.validation.constraints.*;
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

public class CriarLocacaoDTO {

    @NotNull(message = "Data de retirada não pode ser nula.")
    private LocalDate dataRetirada;

    @NotNull(message = "Data de devolucao não pode ser nula.")
    private LocalDate dataPrevistaDevolucao;

    @NotNull(message = "ID do cliente não pode ser nulo.")
    private Long clienteId;

    @NotNull(message = "ID do veiculo não pode ser nulo.")
    private Long veiculoId;

}
