package com.lira.dev.locadora_veiculos.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor


public class LoginRequestDTO {

    private String email;
    private String senha;


}
