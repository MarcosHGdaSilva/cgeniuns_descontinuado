package br.com.challenge.cgeniuns.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.challenge.cgeniuns.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findByCpf(String cpf);
    void deleteByCpf(String cpf);
    // @Query("SELECT cliente FROM Cliente cliente WHERE cliente.cpf_cliente = :cpf_cliente")
    // Cliente findByCpf_cliente(@Param("cpf_cliente") String cpf_cliente);
    // @Modifying
    // @Transactional
    // @Query("DELETE FROM Cliente cliente WHERE cliente.cpf_cliente = :cpf_cliente")
    // void deleteByCpf_cliente(@Param("cpf_cliente") String cpf_cliente);

}
