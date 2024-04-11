package br.com.challenge.cgeniuns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.challenge.cgeniuns.model.Atendente;

public interface AtendenteRepository extends JpaRepository<Atendente, Long> {

    @Query("SELECT atendente FROM Atendente atendente WHERE atendente.cpf = :cpf")
    Atendente findByCpf(@Param("cpf") String cpf);
    void deleteByCpf(String cpf);
    
}
