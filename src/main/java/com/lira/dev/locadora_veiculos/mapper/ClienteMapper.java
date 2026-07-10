package com.lira.dev.locadora_veiculos.mapper;

import com.lira.dev.locadora_veiculos.dto.response.ClienteResponseDTO;
import com.lira.dev.locadora_veiculos.entity.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public ClienteResponseDTO toResponseDTO(Cliente cliente){
        return  ClienteResponseDTO.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .email(cliente.getEmail())
                .telefone(cliente.getTelefone())
                .build();

    }


}
