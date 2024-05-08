package br.com.challenge.cgeniuns.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.challenge.cgeniuns.model.Historico;



public interface HistoricoRepository extends JpaRepository<Historico, Long> {
    @Query("SELECT h FROM History h where h.cpfCliente = :CPF cliente")
    Historico findByCpfCliente(String cpfCliente);
}
