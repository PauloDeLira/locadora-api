package com.lira.dev.locadora_veiculos.mapper;

import com.lira.dev.locadora_veiculos.dto.request.CriarVeiculoDTO;
import com.lira.dev.locadora_veiculos.dto.response.VeiculoResponseDTO;
import com.lira.dev.locadora_veiculos.entity.Veiculo;
import org.springframework.stereotype.Component;

@Component
public class VeiculoMapper {

    public  VeiculoResponseDTO toResponseDTO(Veiculo veiculo){
        return  VeiculoResponseDTO.builder()
                .id(veiculo.getId())
                .marca(veiculo.getMarca())
                .modelo(veiculo.getModelo())
                .ano(veiculo.getAno())
                .cor(veiculo.getCor())
                .valorDiaria(veiculo.getValorDiaria())
                .disponivel(veiculo.isDisponivel())
                .build();

    }

    public Veiculo toEntity(CriarVeiculoDTO dto){
        return Veiculo.builder()
                .modelo(dto.getModelo())
                .marca(dto.getMarca())
                .ano(dto.getAno())
                .cor(dto.getCor())
                .placa(dto.getPlaca())
                .valorDiaria(dto.getValorDiaria())
                .disponivel(dto.isDisponivel())
                .build();
    }




}
