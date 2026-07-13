package com.lira.dev.locadora_veiculos.service;

import com.lira.dev.locadora_veiculos.dto.request.CriarUsuarioDTO;
import com.lira.dev.locadora_veiculos.dto.request.LoginRequestDTO;
import com.lira.dev.locadora_veiculos.dto.response.LoginResponseDTO;
import com.lira.dev.locadora_veiculos.dto.response.UsuarioResponseDTO;
import com.lira.dev.locadora_veiculos.entity.Usuario;
import com.lira.dev.locadora_veiculos.exception.BadRequestException;
import com.lira.dev.locadora_veiculos.exception.UsuarioNotFoundException;
import com.lira.dev.locadora_veiculos.mapper.UsuarioMapper;
import com.lira.dev.locadora_veiculos.repository.UsuarioRepository;
import com.lira.dev.locadora_veiculos.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final JwtService jwtService;

    public UsuarioService(PasswordEncoder passwordEncoder,UsuarioRepository usuarioRepository,UsuarioMapper usuarioMapper,JwtService jwtService){
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.jwtService = jwtService;
    }



    public UsuarioResponseDTO cadastrarUsuario(CriarUsuarioDTO request){
        boolean checarUsuarioExistente = usuarioRepository.existsByEmail(request.getEmail());

        if(checarUsuarioExistente){
            throw new BadRequestException("E-mail já cadastrado.");
        }
        String senhaCriptografada = passwordEncoder.encode(request.getSenha());

        Usuario usuario = Usuario.builder()
                .nome(request.getNome())
                .email(request.getEmail())
                .senha(senhaCriptografada)
                .role(request.getRole())
                .build();

        Usuario novoUsuario = usuarioRepository.save(usuario);

        return usuarioMapper.toResponseDTO(novoUsuario);

    }

    public LoginResponseDTO loginUsuario(LoginRequestDTO request){
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException("E-mail ou senha inválidos."));

        boolean matches = passwordEncoder.matches(request.getSenha(), usuario.getSenha());

        if (!matches){
            throw new BadRequestException("E-mail ou senha inválidos.");
        }

        String token = jwtService.gerarToken(usuario);

        return new LoginResponseDTO(token);
    }





    public Usuario buscarEmailOuFalhar(String email){
        return usuarioRepository.findByEmail(email).orElseThrow(() -> new UsuarioNotFoundException("Usuario com e-mail: " + email + " não encontrado."));
    }


}
