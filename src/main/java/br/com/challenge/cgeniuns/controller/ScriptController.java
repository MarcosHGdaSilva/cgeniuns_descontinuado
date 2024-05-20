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

import br.com.challenge.cgeniuns.model.Script;
import br.com.challenge.cgeniuns.repository.ChamadaRepository;
import br.com.challenge.cgeniuns.repository.ClienteRepository;
import br.com.challenge.cgeniuns.repository.HistoricoRepository;
import br.com.challenge.cgeniuns.repository.ScriptRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("script")
@Slf4j
@Tag(name = "scripts", description = "Endpoint relacionado com scripts")
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
    @Operation(summary = "Lista todos os scripts cadastrados no sistema.", description = "Endpoint que retorna um array de objetos do tipo script")
    public List<Script> index(){
        return  scriptRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Erro de validação do script"),
            @ApiResponse(responseCode = "201", description = "Script cadastrado com sucesso")
    })
    public Script create(@RequestBody Script script){
        log.info("cadastrando script: {}", script);
        verificarExistenciaHistorico(script.getId_compra());
        verificarExistenciaIdChamada(script.getId_chamada());
        verificarExistenciaCliente(script.getCpfCliente());
        return scriptRepository.save(script);
    }

    @GetMapping("{id}")
    @Operation(summary = "Retorna um script especifico cadastrado no sistema.", description = "Endpoint que retorna um objeto do tipo script com um id informado")
    public ResponseEntity<Script> get(@PathVariable Long id){
        log.info("Buscar por id: {}", id);
        return  scriptRepository
        .findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("cpf/{cpf}")
    @Operation(summary = "Retorna um script especifico cadastrado no sistema.", description = "Endpoint que retorna um objeto do tipo script com um cpf informado relacionado a um cliente ou atendente")
    public ResponseEntity<Script> get(@PathVariable String cpf){
        log.info("Buscar por id: {}", cpf);
        Script script = scriptRepository.findByCpf_cliente(cpf);
    if (script != null) {
        return ResponseEntity.ok(script);
    } else {
        return ResponseEntity.notFound().build();
    }
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
        if (!historicoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Histórico não encontrado.");
        }
    }
    
    private void verificarExistenciaIdChamada(Long id){
        if (!chamadaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Chamada não encontrada.");
        }
    }
    
    
    private void verificarExistenciaCliente(String cpf_cliente){
        if (clienteRepository.findByCpf(cpf_cliente) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não existe no cadastro.");
        }
    }
}
