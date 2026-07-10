package com.lira.dev.locadora_veiculos.repository;

import com.lira.dev.locadora_veiculos.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo,Long> {

    public Optional<Veiculo> findByPlaca (String placa);

    public List<Veiculo> findByDisponivelTrue();

    public List<Veiculo> findByMarcaIgnoreCase(String marca);

    public List<Veiculo> findByModeloIgnoreCase(String modelo);

    @Query("SELECT v FROM Veiculo v WHERE v.disponivel = true ORDER BY v.valorDiaria DESC")
    public List<Veiculo> findByVeiculosDisponiveisPorPreco();
}
