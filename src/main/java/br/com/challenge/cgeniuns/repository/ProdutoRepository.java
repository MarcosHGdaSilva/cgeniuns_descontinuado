package br.com.challenge.cgeniuns.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.challenge.cgeniuns.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
}
