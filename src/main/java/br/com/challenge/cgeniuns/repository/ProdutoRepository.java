package br.com.challenge.cgeniuns.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.challenge.cgeniuns.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    @Query("SELECT p FROM Produto p WHERE p.historico.cpf = :cpf")
    Page<Produto> findByCpf(@Param("cpf") String cpf, Pageable pageable);

    Page<Produto> findById(@Param("id") Long id, Pageable pageable);
}
