package com.lira.dev.locadora_veiculos.controller;

import com.lira.dev.locadora_veiculos.dto.request.CriarLocacaoDTO;
import com.lira.dev.locadora_veiculos.dto.response.LocacaoResponseDTO;
import com.lira.dev.locadora_veiculos.exception.ErrorResponse;
import com.lira.dev.locadora_veiculos.service.LocacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Operation(
    summary = "Cadastrar uma nova locação.",
    description = "Cria uma locação para um cliente utilizando um veiculo disponivel, calcula o valor total a ser pago, altera o veiculo utilizado para indisponivel e muda o status da locação para ATIVA.")
    @ApiResponses({
    @ApiResponse(responseCode = "201",description = "Locação criada com sucesso."),
    @ApiResponse(responseCode = "400",description = "Dados invalidos ou veículo indisponivel"),
    @ApiResponse(responseCode = "404",description = "Cliente ou Veiculo não encontrado.",content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
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

    @GetMapping("/page")
    public ResponseEntity<Page<LocacaoResponseDTO>> buscarLocacoesPaginadas(Pageable pageable){
        return ResponseEntity.ok().body(locacaoService.buscarLocacoesPaginadas(pageable));
    }




}
