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

import br.com.challenge.cgeniuns.model.Script;
import br.com.challenge.cgeniuns.repository.ChamadaRepository;
import br.com.challenge.cgeniuns.repository.ClienteRepository;
import br.com.challenge.cgeniuns.repository.HistoricoRepository;
import br.com.challenge.cgeniuns.repository.ScriptRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("script")
@Slf4j
public class ScriptController {
    @Autowired
    ScriptRepository scriptRepository;
    @Autowired
    HistoricoRepository historicoRepository;
    @Autowired
    ChamadaRepository chamadaRepository;
    @Autowired
    ClienteRepository clienteRepository;


    @GetMapping
    public List<Script> index(){
        return  scriptRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Script create(@RequestBody Script script){
        log.info("cadastrando script: {}", script);
        verificarExistenciaHistorico(script.getId_compra());
        verificarExistenciaIdChamada(script.getId_chamada());
        verificarExistenciaCliente(script.getCpf_user());
        return  scriptRepository.save(script);
    }

    @GetMapping("{id}")
    public ResponseEntity<Script> get(@PathVariable Long id){
        log.info("Buscar por id: {}", id);
        return  scriptRepository
        .findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void destroy (@PathVariable Long id){
        log.info("Apagando id {}", id);

        verificarExistencia(id);
        scriptRepository.deleteById(id);
    }

    @PutMapping("{id}")
    public  Script update(@PathVariable Long id, @RequestBody Script script){
        log.info("Atualizando o cadastro do id={} para {}", id, script);

        verificarExistencia(id);
        script.setId(id);
        return scriptRepository.save(script);
    }

    private void verificarExistencia(Long id){
        scriptRepository.
        findById(id)
        .orElseThrow(
            ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "id não encontrado")
        );
    }
    private void verificarExistenciaHistorico(Long id){
    }
    
    private void verificarExistenciaIdChamada(Long id){
    }
    
    private void verificarExistenciaCliente(String cpf){
        if (clienteRepository.findByCpf(cpf) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não existe no cadastro.");
        }
    }
}
