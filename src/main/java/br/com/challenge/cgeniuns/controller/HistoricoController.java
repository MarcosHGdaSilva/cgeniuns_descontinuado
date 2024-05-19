package br.com.challenge.cgeniuns.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.List;

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

import br.com.challenge.cgeniuns.model.Historico;
import br.com.challenge.cgeniuns.repository.ClienteRepository;
import br.com.challenge.cgeniuns.repository.HistoricoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("historico")
@Slf4j
@Tag(name = "historicos", description = "Endpoint relacionado com historicos")
public class HistoricoController {
    @Autowired
    HistoricoRepository historicoRepository;
    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping
    @Operation(summary = "Lista todos os historicos cadastrados no sistema.", description = "Endpoint que retorna um array de objetos do tipo historico")
    public List<Historico> index(){
        return  historicoRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Erro de validação do historico"),
            @ApiResponse(responseCode = "201", description = "Historico cadastrado com sucesso")
    })
    public Historico create(@RequestBody Historico historico){
        log.info("cadastrando historico: {}", historico);
        verificarExistenciaCpfCliente(historico.getCpfCliente());
        return  historicoRepository.save(historico);
    }

    @GetMapping("{id}")
    public ResponseEntity<Historico> get(@PathVariable Long id){
        log.info("Buscar por id: {}", id);
        return  historicoRepository
        .findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("cpf/{cpfCliente}")
    public ResponseEntity<Historico> get(@PathVariable String cpfCliente){
        log.info("Buscar por CPF: {}", cpfCliente);
        Historico historico = historicoRepository.findByCpfCliente(cpfCliente);
    if (historico != null) {
        return ResponseEntity.ok(historico);
    } else {
        return ResponseEntity.notFound().build();
    }
    }
    
    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void destroy (@PathVariable Long id){
        log.info("Apagando id {}", id);
        verificarExistencia(id);
        historicoRepository.deleteById(id);
    }

    @PutMapping("{id}")
    public  Historico update(@PathVariable Long id, @RequestBody Historico historico){
        log.info("Atualizando o cadastro do id={} para {}", id, historico);

        verificarExistencia(id);
        historico.setId(id);
        return historicoRepository.save(historico);
    }

    private void verificarExistencia(Long id){
        historicoRepository.
        findById(id)
        .orElseThrow(
            ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "id não encontrado")
        );
    }

    private void verificarExistenciaCpfCliente(String cpf_cliente){
        if (clienteRepository.findByCpf(cpf_cliente) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não existe no cadastro.");
        }
    }
}
