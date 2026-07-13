package com.lira.dev.locadora_veiculos.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data


public class LoginResponseDTO {

    private String token;

}
