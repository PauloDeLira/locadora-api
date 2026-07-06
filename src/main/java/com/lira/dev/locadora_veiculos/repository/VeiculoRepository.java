package com.lira.dev.locadora_veiculos.repository;

import com.lira.dev.locadora_veiculos.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo,Long> {
}
