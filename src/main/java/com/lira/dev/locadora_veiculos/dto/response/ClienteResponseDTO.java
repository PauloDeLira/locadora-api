package com.lira.dev.locadora_veiculos.dto.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ClienteResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private String telefone;

}
