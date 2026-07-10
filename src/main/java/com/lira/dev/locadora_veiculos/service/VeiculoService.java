package com.lira.dev.locadora_veiculos.service;

import com.lira.dev.locadora_veiculos.dto.request.AtualizarVeiculoDTO;
import com.lira.dev.locadora_veiculos.dto.request.CriarVeiculoDTO;
import com.lira.dev.locadora_veiculos.dto.response.VeiculoResponseDTO;
import com.lira.dev.locadora_veiculos.entity.Veiculo;
import com.lira.dev.locadora_veiculos.exception.VeiculoNotFoundException;
import com.lira.dev.locadora_veiculos.mapper.VeiculoMapper;
import com.lira.dev.locadora_veiculos.repository.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoService {

private final VeiculoRepository veiculoRepository;
private final VeiculoMapper veiculoMapper;

public VeiculoService(VeiculoRepository veiculoRepository,VeiculoMapper veiculoMapper){
    this.veiculoRepository = veiculoRepository;
    this.veiculoMapper = veiculoMapper;
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
    return veiculoMapper.toResponseDTO(veiculo);
}

public VeiculoResponseDTO buscarVeiculoPorPlaca(String placa){
    Veiculo veiculo = buscarPlacaOuFalhar(placa);
    return veiculoMapper.toResponseDTO(veiculo);
}

public List<VeiculoResponseDTO> buscarVeiculosDisponiveis(){
    return veiculoRepository.findByDisponivelTrue()
            .stream()
            .map(veiculoMapper::toResponseDTO)
            .toList();
}

public List<VeiculoResponseDTO> buscarVeiculosPorMarca(String marca){
    return veiculoRepository.findByMarcaIgnoreCase(marca)
            .stream()
            .map(veiculoMapper::toResponseDTO)
            .toList();
}

public List<VeiculoResponseDTO> buscarVeiculosPorModelo(String modelo){
    return veiculoRepository.findByModeloIgnoreCase(modelo)
            .stream()
            .map(veiculoMapper::toResponseDTO)
            .toList();
}

public List<VeiculoResponseDTO> buscarVeiculosDisponiveisPorPreco(){
        return veiculoRepository.findByVeiculosDisponiveisPorPreco()
                .stream()
                .map(veiculoMapper::toResponseDTO)
                .toList();
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

    return veiculoMapper.toResponseDTO(veiculoNovo);
}

public VeiculoResponseDTO atualizarVeiculoPorId(Long id, AtualizarVeiculoDTO request){
    Veiculo veiculo = buscarIdOuFalhar(id);

    veiculo.setCor(request.getCor());
    veiculo.setValorDiaria(request.getValorDiaria());
    veiculo.setDisponivel(request.isDisponivel());

    Veiculo veiculoAtualizado = veiculoRepository.save(veiculo);

    return veiculoMapper.toResponseDTO(veiculoAtualizado);

}


public void deletarVeiculoPorId(Long id){
    Veiculo veiculo = buscarIdOuFalhar(id);
    veiculoRepository.delete(veiculo);
}


public Veiculo buscarIdOuFalhar(Long id){
    return veiculoRepository.findById(id).orElseThrow(() -> new VeiculoNotFoundException("Veiculo de ID: " + id + " não encontrado."));
}

public Veiculo buscarPlacaOuFalhar(String placa){
    return veiculoRepository.findByPlaca(placa).orElseThrow(() -> new VeiculoNotFoundException("Veiculo com placa: " + placa + " não encontrado."));
}

}
