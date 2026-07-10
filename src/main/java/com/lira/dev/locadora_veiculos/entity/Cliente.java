package com.lira.dev.locadora_veiculos.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


@Entity(name = "tb_clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String cpf;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String telefone;

    @Column(unique = true)
    private String numeroCNH;

    @OneToMany(mappedBy = "cliente")
    private List<Locacao> locacoes = new ArrayList<>();

}
