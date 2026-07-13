package com.lira.dev.locadora_veiculos.controller;

import com.lira.dev.locadora_veiculos.dto.request.LoginRequestDTO;
import com.lira.dev.locadora_veiculos.dto.response.LoginResponseDTO;
import com.lira.dev.locadora_veiculos.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> fazerLogin(@RequestBody @Valid LoginRequestDTO request){
        return ResponseEntity.ok().body(usuarioService.loginUsuario(request));
    }



}
