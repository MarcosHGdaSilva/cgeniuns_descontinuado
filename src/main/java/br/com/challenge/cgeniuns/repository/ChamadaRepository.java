package br.com.challenge.cgeniuns.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.challenge.cgeniuns.model.Chamada;

public interface ChamadaRepository extends JpaRepository<Chamada, Long>{
    
}
