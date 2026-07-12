package com.lira.dev.locadora_veiculos.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class ErrorResponse {
@Schema(description = "Mensagem descritiva do erro", example = "Veículo não encontrado")
    private String msg;

@Schema(description = "Código HTTP do erro", example = "404")
    private int status;

@Schema(description = "Data e hora em que o erro ocorreu", example = "2026-07-12T10:15:30")
    private LocalDateTime timestamp;
}
