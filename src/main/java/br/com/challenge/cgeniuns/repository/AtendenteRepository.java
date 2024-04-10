package br.com.challenge.cgeniuns.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.challenge.cgeniuns.model.Atendente;

public interface AtendenteRepository extends JpaRepository<Atendente, Long> {

    // Optional<Atendente> findByCpf(String cpf);

    // Object deleteByCpf(String cpf);

    // Optional<Atendente> findById(String cpf);
    
}
