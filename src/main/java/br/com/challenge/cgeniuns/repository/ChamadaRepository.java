package br.com.challenge.cgeniuns.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.challenge.cgeniuns.model.Chamada;

public interface ChamadaRepository extends JpaRepository<Chamada, Long>{
    @Query("SELECT c FROM Chamada c WHERE c.cliente.cpf = :cpf OR c.atendente.cpf = :cpf")
    Page<Chamada> findByCpf(@Param("cpf") String cpf, Pageable pageable);
}