package br.com.challenge.cgeniuns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.challenge.cgeniuns.model.Historico;



public interface HistoricoRepository extends JpaRepository<Historico, Long> {
    @Query("SELECT h FROM Historico h WHERE h.cpfCliente = :cpfCliente")
    Historico findByCpfCliente(String cpfCliente);
}
