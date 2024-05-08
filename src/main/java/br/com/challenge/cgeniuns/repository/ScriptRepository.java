package br.com.challenge.cgeniuns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.challenge.cgeniuns.model.Script;


public interface ScriptRepository extends JpaRepository<Script, Long>{
    @Query("SELECT s FROM Script s WHERE s.cpfCliente =:cpf")
    Script findByCpf_cliente(String cpf);
}
