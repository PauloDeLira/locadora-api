package com.lira.dev.locadora_veiculos.dto.request;

import com.lira.dev.locadora_veiculos.enums.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class CriarUsuarioDTO {

    @NotBlank
    @Size(min = 2,
    max = 100,
    message = "Campo deve ter entre 2 e 100 caracteres")
    private String nome;

    @NotBlank(message = "E-mail não pode ser vazio")
    @Email(message = "O e-mail deve ser um endereço válido")
    private String email;

    @NotBlank(message = "Senha não pode ser vazia")
    @Size(min = 8,
    max = 20,
    message = "Senha deve ter entre 8 e 20 caracteres.")
    private String senha;

    @NotNull
    private Role role;
}
