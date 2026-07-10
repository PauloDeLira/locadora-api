package com.lira.dev.locadora_veiculos.repository;

import com.lira.dev.locadora_veiculos.entity.Locacao;
import com.lira.dev.locadora_veiculos.enums.StatusLocacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocacaoRepository extends JpaRepository<Locacao, Long> {

    public List<Locacao> findByClienteId(Long id);

    public List<Locacao> findByStatus(StatusLocacao status);

}
