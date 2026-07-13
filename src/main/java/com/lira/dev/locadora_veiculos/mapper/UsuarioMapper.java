package com.lira.dev.locadora_veiculos.mapper;


import com.lira.dev.locadora_veiculos.dto.response.UsuarioResponseDTO;
import com.lira.dev.locadora_veiculos.entity.Cliente;
import com.lira.dev.locadora_veiculos.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioResponseDTO toResponseDTO(Usuario usuario){
        return  UsuarioResponseDTO.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .role(usuario.getRole())
                .build();
    }
}
