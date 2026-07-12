package com.lira.dev.locadora_veiculos.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class CriarLocacaoDTO {

    @Schema(description = "Data de retirada do veiculo", example = "2026-07-12")
    @NotNull(message = "Data de retirada não pode ser nula.")
    private LocalDate dataRetirada;

    @Schema(description = "Data prevista para devolução do veiculo", example = "2026-12-12")
    @NotNull(message = "Data de devolucao não pode ser nula.")
    private LocalDate dataPrevistaDevolucao;

    @Schema(description = "ID do cliente", example = "1")
    @NotNull(message = "ID do cliente não pode ser nulo.")
    private Long clienteId;

    @Schema(description = "ID do veiculo", example = "2")
    @NotNull(message = "ID do veiculo não pode ser nulo.")
    private Long veiculoId;

}
