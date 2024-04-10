package br.com.challenge.cgeniuns.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.challenge.cgeniuns.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Optional<Cliente> findByCpf(String cpf);

    // Object deleteByCpf(String cpf);

    // Optional<Cliente> findById(String cpf);
}
