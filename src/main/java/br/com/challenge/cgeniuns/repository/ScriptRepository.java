package br.com.challenge.cgeniuns.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.challenge.cgeniuns.model.Script;

public interface ScriptRepository extends JpaRepository<Script, Long>{
    
}
