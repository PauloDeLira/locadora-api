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

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoResponseDTO> listarVeiculoPorId(@PathVariable Long id){
        return ResponseEntity.ok(veiculoService.listarVeiculoPorId(id));
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
