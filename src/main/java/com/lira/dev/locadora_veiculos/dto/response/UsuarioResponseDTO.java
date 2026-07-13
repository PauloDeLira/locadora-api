package com.lira.dev.locadora_veiculos.dto.response;

import com.lira.dev.locadora_veiculos.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class UsuarioResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private Role role;

}
