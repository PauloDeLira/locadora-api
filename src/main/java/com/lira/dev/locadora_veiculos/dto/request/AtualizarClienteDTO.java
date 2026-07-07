package com.lira.dev.locadora_veiculos.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AtualizarClienteDTO {

    @NotBlank(message = "E-mail não pode ser vazio")
    @Email(message = "O e-mail deve ser um endereço válido")
    private String email;

    @NotBlank(message = "Telefone não pode ser vazio")
    @Size(min = 11,
    max = 11,
    message = "Telefone deve ter os 11 Digitos (XX) 9XXXX-XXXX")
    private String telefone;

}
