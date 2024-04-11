package br.com.challenge.cgeniuns.controller;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.challenge.cgeniuns.model.Chamada;
import br.com.challenge.cgeniuns.repository.ChamadaRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("chamada")
@Slf4j
public class ChamadaController {
    @Autowired
    ChamadaRepository chamadaRepository;

    @GetMapping
    public List<Chamada> index(){
        return  chamadaRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Chamada create(@RequestBody Chamada chamada){
        log.info("cadastrando chamada: {}", chamada);
        return  chamadaRepository.save(chamada);
    }

    @GetMapping("{id}")
    public ResponseEntity<Chamada> get(@PathVariable Long id){
        log.info("Buscar por id: {}", id);
        return  chamadaRepository
        .findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void destroy (@PathVariable Long id){
        log.info("Apagando id {}", id);

        verificarExistencia(id);
        chamadaRepository.deleteById(id);
    }

    @PutMapping("{id}")
    public  Chamada update(@PathVariable Long id, @RequestBody Chamada chamada){
        log.info("Atualizando o cadastro do id={} para {}", id, chamada);

        verificarExistencia(id);
        chamada.setId(id);
        return chamadaRepository.save(chamada);
    }

    private void verificarExistencia(Long id){
        chamadaRepository.
        findById(id)
        .orElseThrow(
            ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "id não encontrado")
        );
    }

}
