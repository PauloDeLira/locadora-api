package com.lira.dev.locadora_veiculos.repository;

import com.lira.dev.locadora_veiculos.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {

    public List<Cliente> findByNomeIgnoreCaseContaining(String nome);

    public Optional<Cliente> findByCpf(String cpf);

    public Page<Cliente> findAll(Pageable pageable);

}
