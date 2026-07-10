package com.lira.dev.locadora_veiculos.controller;


import com.lira.dev.locadora_veiculos.dto.request.AtualizarVeiculoDTO;
import com.lira.dev.locadora_veiculos.dto.request.CriarVeiculoDTO;
import com.lira.dev.locadora_veiculos.dto.response.VeiculoResponseDTO;
import com.lira.dev.locadora_veiculos.service.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("NullableProblems")
@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoService veiculoService;

    public VeiculoController (VeiculoService veiculoService){
        this.veiculoService = veiculoService;
    }


    @GetMapping
    public ResponseEntity<List<VeiculoResponseDTO>> listarTodosVeiculos(){
        return ResponseEntity.ok(veiculoService.listarTodosVeiculos());
    }

    @GetMapping("/disponiveis")
    public ResponseEntity<List<VeiculoResponseDTO>> buscarVeiculosDisponiveis(){
        return ResponseEntity.ok().body(veiculoService.buscarVeiculosDisponiveis());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoResponseDTO> listarVeiculoPorId(@PathVariable Long id){
        return ResponseEntity.ok(veiculoService.listarVeiculoPorId(id));
    }

    @GetMapping ("/placa/{placa}")
    public ResponseEntity<VeiculoResponseDTO>buscarVeiculoPorPlaca(@PathVariable String placa){
        return ResponseEntity.ok().body(veiculoService.buscarVeiculoPorPlaca(placa));
    }

    @GetMapping("/marca/{marca}")
    public ResponseEntity<List<VeiculoResponseDTO>> buscarVeiculosPorMarca(@PathVariable String marca){
        return ResponseEntity.ok().body(veiculoService.buscarVeiculosPorMarca(marca));
    }

    @GetMapping("/modelo/{modelo}")
    public ResponseEntity<List<VeiculoResponseDTO>> buscarVeiculosPorModelo(@PathVariable String modelo){
        return ResponseEntity.ok().body(veiculoService.buscarVeiculosPorModelo(modelo));
    }

    @GetMapping("/disponiveis/order/preco")
    public ResponseEntity<List<VeiculoResponseDTO>> buscarVeiculosDisponiveisPorPreco(){
        return ResponseEntity.ok().body(veiculoService.buscarVeiculosDisponiveisPorPreco());
    }

    @PostMapping
    public ResponseEntity<VeiculoResponseDTO> cadastrarVeiculo(@Valid @RequestBody CriarVeiculoDTO request){
        return ResponseEntity.status(HttpStatus.CREATED).body(veiculoService.cadastrarVeiculo(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeiculoResponseDTO> atualizarVeiculoPorId( @PathVariable Long id, @Valid @RequestBody AtualizarVeiculoDTO request){
        return ResponseEntity.ok(veiculoService.atualizarVeiculoPorId(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVeiculoPorId(@PathVariable Long id){
        veiculoService.deletarVeiculoPorId(id);
        return ResponseEntity.noContent().build();
    }




}
