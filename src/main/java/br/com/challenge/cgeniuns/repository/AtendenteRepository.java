package br.com.challenge.cgeniuns.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.challenge.cgeniuns.model.Atendente;

public interface AtendenteRepository extends JpaRepository<Atendente, Long> {
    Atendente findByCpf(String cpf);
    void deleteByCpf(String cpf);
    // @Query("SELECT atendente FROM Atendente atendente WHERE atendente.cpf_atendente = :cpf_atendente")
    // Atendente findByCpf(@Param("cpf_atendente") String cpf_atendente);
    // @Modifying
    // @Transactional
    // @Query("DELETE FROM Atendente atendente WHERE atendente.cpf_atendente = :cpf_atendente")
    // void deleteByCpf_atendente(@Param("cpf_atendente") String cpf_atendente);
    
}
