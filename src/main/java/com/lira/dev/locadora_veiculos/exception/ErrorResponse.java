package com.lira.dev.locadora_veiculos.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class ErrorResponse {
    private String msg;
    private int status;
    private LocalDateTime timestamp;
}
