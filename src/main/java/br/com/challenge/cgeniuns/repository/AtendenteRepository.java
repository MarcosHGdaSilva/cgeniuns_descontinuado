package br.com.challenge.cgeniuns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.challenge.cgeniuns.model.Atendente;

public interface AtendenteRepository extends JpaRepository<Atendente, Long> {

    @Query("SELECT atendente FROM Atendente atendente WHERE atendente.cpf_atendente = :cpf_atendente")
    Atendente findByCpf_atendente(@Param("cpf_atendente") String cpf_atendentepf);
    @Query("SELECT atendente FROM Atendente atendente WHERE atendente.cpf_atendente = :cpf_atendente")
    void deleteByCpf_atendente(@Param("cpf_atendente") String cpf_atendente);
    
}
