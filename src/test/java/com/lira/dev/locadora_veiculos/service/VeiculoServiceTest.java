package com.lira.dev.locadora_veiculos.service;

import com.lira.dev.locadora_veiculos.dto.request.CriarVeiculoDTO;
import com.lira.dev.locadora_veiculos.dto.response.VeiculoResponseDTO;
import com.lira.dev.locadora_veiculos.entity.Veiculo;
import com.lira.dev.locadora_veiculos.mapper.VeiculoMapper;
import com.lira.dev.locadora_veiculos.repository.VeiculoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VeiculoServiceTest {

    @Mock
    private VeiculoRepository veiculoRepository;

    @Mock
    private VeiculoMapper veiculoMapper;

    @InjectMocks
    private VeiculoService veiculoService;

    @Test
    void deveCadastrarVeiculoComSucesso() {

        CriarVeiculoDTO dto = CriarVeiculoDTO.builder()
                .marca("Chevrolet")
                .modelo("Onix")
                .ano(2021)
                .placa("ABC0B23")
                .cor("Prata")
                .valorDiaria(new BigDecimal("200.00"))
                .disponivel(true)
                .build();

        Veiculo veiculoSalvo = Veiculo.builder()
                .id(1L)
                .marca("Chevrolet")
                .modelo("Onix")
                .ano(2021)
                .placa("ABC0B23")
                .cor("Prata")
                .valorDiaria(new BigDecimal("200.00"))
                .disponivel(true)
                .build();

        VeiculoResponseDTO responseDTO = VeiculoResponseDTO.builder()
                .id(1L)
                .marca("Chevrolet")
                .modelo("Onix")
                .ano(2021)
                .cor("Prata")
                .valorDiaria(new BigDecimal("200.00"))
                .disponivel(true)
                .build();

        when(veiculoRepository.save(any(Veiculo.class)))
                .thenReturn(veiculoSalvo);

        when(veiculoMapper.toResponseDTO(veiculoSalvo))
                .thenReturn(responseDTO);

        VeiculoResponseDTO resultado = veiculoService.cadastrarVeiculo(dto);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Chevrolet", resultado.getMarca());
        assertEquals("Onix", resultado.getModelo());
        assertEquals(new BigDecimal("200.00"), resultado.getValorDiaria());
        assertTrue(resultado.isDisponivel());
    }
}