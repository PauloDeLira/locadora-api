package com.lira.dev.locadora_veiculos.service;

import com.lira.dev.locadora_veiculos.dto.request.AtualizarClienteDTO;
import com.lira.dev.locadora_veiculos.dto.request.CriarClienteDTO;
import com.lira.dev.locadora_veiculos.dto.response.ClienteResponseDTO;
import com.lira.dev.locadora_veiculos.entity.Cliente;
import com.lira.dev.locadora_veiculos.exception.ClienteNotFoundException;
import com.lira.dev.locadora_veiculos.mapper.ClienteMapper;
import com.lira.dev.locadora_veiculos.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteService(ClienteRepository clienteRepository,ClienteMapper clienteMapper){
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    public List<ClienteResponseDTO> listarTodosClientes(){
        return clienteRepository.findAll().stream()
                .map(c -> ClienteResponseDTO.builder()
                        .id(c.getId())
                        .nome(c.getNome())
                        .email(c.getEmail())
                        .telefone(c.getTelefone())
                        .build())
                        .toList();
    }


    public ClienteResponseDTO listarClientePorId(Long id){
        Cliente cliente = buscarIdOuFalhar(id);
        return clienteMapper.toResponseDTO(cliente);
    }


    public ClienteResponseDTO cadastrarCliente(CriarClienteDTO request){
        Cliente cliente = Cliente.builder()
                .nome(request.getNome())
                .email(request.getEmail())
                .telefone(request.getTelefone())
                .cpf(request.getCpf())
                .numeroCNH(request.getNumeroCNH())
                .build();

        Cliente novoCliente = clienteRepository.save(cliente);

        return clienteMapper.toResponseDTO(novoCliente);
    }


    public ClienteResponseDTO atualizarCliente(Long id,AtualizarClienteDTO request){
        Cliente cliente = buscarIdOuFalhar(id);

        cliente.setTelefone(request.getTelefone());
        cliente.setEmail(request.getEmail());

        Cliente clienteAtualizado = clienteRepository.save(cliente);

        return clienteMapper.toResponseDTO(clienteAtualizado);
    }

    public void deletarClientePorId(Long id){
        Cliente cliente = buscarIdOuFalhar(id);
        clienteRepository.delete(cliente);
    }


    public Cliente buscarIdOuFalhar(Long id){
        return clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException("Cliente de ID: " + id + " não encontrado."));
    }



}
