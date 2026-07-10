package com.lira.dev.locadora_veiculos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tb_veiculos")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marca;
    private String modelo;
    private Integer ano;
    private String placa;
    private String cor;
    private BigDecimal valorDiaria;
    private boolean disponivel;

    @OneToMany(mappedBy = "veiculo")
    private List<Locacao> locacoes = new ArrayList<>();
}
