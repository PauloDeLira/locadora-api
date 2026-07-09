package com.lira.dev.locadora_veiculos.controller;

import com.lira.dev.locadora_veiculos.dto.request.CriarLocacaoDTO;
import com.lira.dev.locadora_veiculos.dto.response.LocacaoResponseDTO;
import com.lira.dev.locadora_veiculos.service.LocacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locacoes")
public class LocacaoController {

    private final LocacaoService locacaoService;

    public LocacaoController(LocacaoService locacaoService){
        this.locacaoService = locacaoService;
    }

    @PostMapping
    public ResponseEntity<LocacaoResponseDTO> cadastrarLocacao(@RequestBody @Valid CriarLocacaoDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(locacaoService.cadastrarLocacao(dto));
    }

    @PatchMapping("/{id}/devolver")
    public ResponseEntity<LocacaoResponseDTO> devolverLocacao(@PathVariable Long id){
        return ResponseEntity.ok().body(locacaoService.devolverLocacao(id));
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<LocacaoResponseDTO> cancelarLocacao(@PathVariable Long id){
        return ResponseEntity.ok().body(locacaoService.cancelarLocacao(id));
    }

    @GetMapping
    public ResponseEntity<List<LocacaoResponseDTO>> buscarLocacoes(){
        return ResponseEntity.ok().body(locacaoService.listarLocacoes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocacaoResponseDTO> buscarLocacaoPorId(@PathVariable Long id){
        return ResponseEntity.ok().body(locacaoService.listarLocacaoPorId(id));
    }




}
