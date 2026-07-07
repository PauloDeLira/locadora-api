package com.lira.dev.locadora_veiculos.controller;

import com.lira.dev.locadora_veiculos.dto.request.AtualizarClienteDTO;
import com.lira.dev.locadora_veiculos.dto.request.CriarClienteDTO;
import com.lira.dev.locadora_veiculos.dto.response.ClienteResponseDTO;
import com.lira.dev.locadora_veiculos.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }


    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listarTodosClientes(){
        return ResponseEntity.ok(clienteService.listarTodosClientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> listarClientePorId(@PathVariable Long id){
        return ResponseEntity.ok(clienteService.listarClientePorId(id));
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> cadastrarCliente(@Valid @RequestBody CriarClienteDTO request){
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.cadastrarCliente(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizarCliente(@PathVariable Long id, @Valid @RequestBody AtualizarClienteDTO request){
        return ResponseEntity.ok().body(clienteService.atualizarCliente(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarClientePorId(@PathVariable Long id){
        clienteService.deletarClientePorId(id);
        return ResponseEntity.noContent().build();
    }

}
