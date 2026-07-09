package com.lira.dev.locadora_veiculos.service;

import com.lira.dev.locadora_veiculos.dto.request.AtualizarVeiculoDTO;
import com.lira.dev.locadora_veiculos.dto.request.CriarVeiculoDTO;
import com.lira.dev.locadora_veiculos.dto.response.VeiculoResponseDTO;
import com.lira.dev.locadora_veiculos.entity.Veiculo;
import com.lira.dev.locadora_veiculos.exception.VeiculoNotFoundException;
import com.lira.dev.locadora_veiculos.repository.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoService {

private final VeiculoRepository veiculoRepository;


public VeiculoService(VeiculoRepository veiculoRepository){
    this.veiculoRepository = veiculoRepository;
}

public List<VeiculoResponseDTO> listarTodosVeiculos(){
    List<Veiculo> veiculos = veiculoRepository.findAll();
    return veiculos.stream()
            .map(v -> VeiculoResponseDTO.builder()
                    .id(v.getId())
                    .marca(v.getMarca())
                    .modelo(v.getModelo())
                    .ano(v.getAno())
                    .cor(v.getCor())
                    .valorDiaria(v.getValorDiaria())
                    .disponivel(v.isDisponivel())
                    .build())
            .toList();
}

public VeiculoResponseDTO listarVeiculoPorId(Long id){
    Veiculo veiculo = buscarIdOuFalhar(id);
    return returnResponseDTO(veiculo);
}


public VeiculoResponseDTO cadastrarVeiculo(CriarVeiculoDTO request){
    Veiculo veiculo = Veiculo.builder()
            .marca(request.getMarca())
            .modelo(request.getModelo())
            .ano(request.getAno())
            .placa(request.getPlaca())
            .cor(request.getCor())
            .valorDiaria(request.getValorDiaria())
            .disponivel(request.isDisponivel())
            .build();

    Veiculo veiculoNovo = veiculoRepository.save(veiculo);

    return returnResponseDTO(veiculoNovo);
}

public VeiculoResponseDTO atualizarVeiculoPorId(Long id, AtualizarVeiculoDTO request){
    Veiculo veiculo = buscarIdOuFalhar(id);

    veiculo.setCor(request.getCor());
    veiculo.setValorDiaria(request.getValorDiaria());
    veiculo.setDisponivel(request.isDisponivel());

    Veiculo veiculoAtualizado = veiculoRepository.save(veiculo);

    return returnResponseDTO(veiculoAtualizado);

}


public void deletarVeiculoPorId(Long id){
    Veiculo veiculo = buscarIdOuFalhar(id);
    veiculoRepository.delete(veiculo);
}




public Veiculo buscarIdOuFalhar(Long id){
    return veiculoRepository.findById(id).orElseThrow(() -> new VeiculoNotFoundException("Veiculo de ID: " + id + " não encontrado."));
}

public static VeiculoResponseDTO returnResponseDTO(Veiculo veiculo){
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
}
