package com.lira.dev.locadora_veiculos.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class CriarClienteDTO {

    @NotBlank(message = "Nome não pode ser vazio.")
    @Size(min = 2,
    max = 100,
    message = "Campo deve ter entre 2 e 100 caracteres")
    private String nome;

    @NotBlank(message = "CPF não pode ser vazio ")
    @Size(min = 11,
    max = 11,
    message = "CPF tem que ter 11 dígitos.")
    private String cpf;

    @NotBlank(message = "E-mail não pode ser vazio")
    @Email(message = "O e-mail deve ser um endereço válido")
    private String email;

    @NotBlank(message = "Telefone não pode ser vazio")
    @Size(min = 11,
    max = 11,
    message = "Telefone deve ter os 11 Digitos (XX) 9XXXX-XXXX")
    private String telefone;

    @NotBlank(message = "CNH não pode ser vazio ")
    @Size(min = 11,
    max = 11,
    message = "CNH tem que ter 11 dígitos.")
    private String numeroCNH;

}
