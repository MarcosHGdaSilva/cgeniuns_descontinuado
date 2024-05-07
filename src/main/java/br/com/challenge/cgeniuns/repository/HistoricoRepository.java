package br.com.challenge.cgeniuns.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.challenge.cgeniuns.model.Historico;



public interface HistoricoRepository extends JpaRepository<Historico, Long> {
    Historico findByCpfCliente(String cpfCliente);
    Optional<Historico> findById(Long id);
}
