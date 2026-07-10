package com.lira.dev.locadora_veiculos.mapper;

import com.lira.dev.locadora_veiculos.dto.response.LocacaoResponseDTO;
import com.lira.dev.locadora_veiculos.entity.Locacao;
import org.springframework.stereotype.Component;

@Component
public class LocacaoMapper {

    public LocacaoResponseDTO toResponseDTO(Locacao locacao){
        return LocacaoResponseDTO.builder()
                .id(locacao.getId())
                .dataRetirada(locacao.getDataRetirada())
                .dataPrevistaDevolucao(locacao.getDataPrevistaDevolucao())
                .dataDevolucao(locacao.getDataDevolucao())
                .valorTotal(locacao.getValorTotal())
                .status(locacao.getStatus())
                .clienteId(locacao.getCliente().getId())
                .nomeCliente(locacao.getCliente().getNome())
                .veiculoId(locacao.getVeiculo().getId())
                .modeloVeiculo(locacao.getVeiculo().getModelo())
                .placaVeiculo(locacao.getVeiculo().getPlaca())
                .build();
    }



}
