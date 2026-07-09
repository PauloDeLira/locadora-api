package com.lira.dev.locadora_veiculos.service;

import com.lira.dev.locadora_veiculos.dto.response.LocacaoResponseDTO;
import com.lira.dev.locadora_veiculos.repository.LocacaoRepository;
import org.springframework.stereotype.Service;

@Service
public class LocacaoService {

    private final LocacaoRepository locacaoRepository;

    public LocacaoService(LocacaoRepository locacaoRepository){
        this.locacaoRepository = locacaoRepository;
    }


    public LocacaoResponseDTO cadastrarLocacao(){

    }


}
