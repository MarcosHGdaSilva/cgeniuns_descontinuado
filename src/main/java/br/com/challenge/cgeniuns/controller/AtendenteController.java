package br.com.challenge.cgeniuns.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.challenge.cgeniuns.model.Atendente;
import br.com.challenge.cgeniuns.repository.AtendenteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RequestMapping("atendente")
@Slf4j
public class AtendenteController {
    
    @Autowired
    AtendenteRepository atendenteRepository;

    @GetMapping
    public List<Atendente> index(){
        return atendenteRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Atendente create(@RequestBody Atendente atendente){
        log.info("cadastrando atendente: {}", atendente);
    if (atendenteRepository.findByCpf(atendente.getCpf()) == null) {
        return atendenteRepository.save(atendente);
    }else {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Atendente já cadastrado");
    }
}

    @GetMapping("{id}")
    public ResponseEntity<Atendente> get(@PathVariable Long id){
        log.info("Buscar por id: {}", id);
        return atendenteRepository
        .findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("cpf/{cpf_atendente}")
    public ResponseEntity<Atendente> get(@PathVariable String cpf_atendente){
        log.info("Buscar por CPF: {}", cpf_atendente);
        Atendente atendente = atendenteRepository.findByCpf(cpf_atendente);
    if (atendente != null) {
        return ResponseEntity.ok(atendente);
    } else {
        return ResponseEntity.notFound().build();
    }
    }
    @GetMapping("login")
    public Atendente Login(@RequestParam String cpf, @RequestParam String senha) {
        return atendenteRepository.login(cpf, senha);
    }
    
    
    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void destroy (@PathVariable Long id){
        log.info("Apagando id {}", id);

        verificarId(id);
        atendenteRepository.deleteById(id);
    }
    
    @Transactional
    @DeleteMapping("cpf/{cpf_atendente}")
    @ResponseStatus(NO_CONTENT)
    public void deleteByCpf_atendente (@PathVariable String cpf_atendente){
        log.info("Apagando Atendente com CPF {}", cpf_atendente);
        verificarCpf(cpf_atendente);
        atendenteRepository.deleteByCpf(cpf_atendente);
    }

    @PutMapping("{id}")
    public Atendente update(@PathVariable Long id, @RequestBody Atendente atendente){
        log.info("Atualizando o cadastro do id={} para {}", id, atendente);
        verificarId(id);
        atendente.setId(id);
        return atendenteRepository.save(atendente);
    }

    @PutMapping("cpf/{cpf_atendente}")
    public Atendente update(@PathVariable String cpf_atendente, @RequestBody Atendente atendente){
        log.info("Atualizando o cadastro do id={} para {}", cpf_atendente, atendente);
        Atendente atendenteSalvo = verificarCpf(cpf_atendente);
        atendenteSalvo.setNome_atendente(atendente.getNome_atendente());
        atendenteSalvo.setCpf(atendente.getCpf());
        atendenteSalvo.setSetor(atendente.getSetor());
        atendenteSalvo.setSenha(atendente.getSenha());
        atendenteSalvo.setAvaliacao_atendente(atendente.getAvaliacao_atendente());
        return atendenteRepository.save(atendenteSalvo);
    }

    private void verificarId(Long id){
        atendenteRepository.
        findById(id)
        .orElseThrow(
            ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "id não encontrado")
        );
    }
    private Atendente verificarCpf(String cpf_atendente){
        Atendente atendente = atendenteRepository.findByCpf(cpf_atendente);
    if (atendente == null) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Atendente com CPF não encontrado");
    }else{
        return atendente;
    }
}
}
