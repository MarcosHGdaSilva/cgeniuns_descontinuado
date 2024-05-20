package br.com.challenge.cgeniuns.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.challenge.cgeniuns.model.Atendente;
import br.com.challenge.cgeniuns.model.Chamada;
import br.com.challenge.cgeniuns.model.Cliente;
import br.com.challenge.cgeniuns.repository.AtendenteRepository;
import br.com.challenge.cgeniuns.repository.ChamadaRepository;
import br.com.challenge.cgeniuns.repository.ClienteRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("chamada")
@Slf4j
@Tag(name = "chamadas", description = "Endpoint relacionado com chamadas")
public class ChamadaController {
    @Autowired
    ChamadaRepository chamadaRepository;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    AtendenteRepository atendenteRepository;

    @GetMapping
    @Operation(summary = "Lista todos os chamadas cadastrados no sistema.", description = "Endpoint que retorna um array de objetos do tipo chamada")
    public Page<Chamada> index(
        @RequestParam(required = false) String cpf,
        @ParameterObject @PageableDefault(sort = "dtChamada", direction = Direction.DESC) Pageable pageable
    ){
        if (cpf != null){
            return chamadaRepository.findByCpf(cpf, pageable);
        }

        return  chamadaRepository.findAll(pageable);
    }

    
    @PostMapping
    @ResponseStatus(CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Erro de validação do chamadas"),
            @ApiResponse(responseCode = "201", description = "Chamada cadastrado com sucesso")
    })
    public Chamada create(@RequestBody Chamada chamada){
        log.info("cadastrando chamada: {}", chamada);
        verificarExistenciaCpfAtendente(chamada.getAtendente());
        verificarExistenciaCpfCliente(chamada.getCliente());
        return  chamadaRepository.save(chamada);
    }

    @GetMapping("{id}")
    @Operation(summary = "Retorna um chamada especifico cadastrado no sistema.", description = "Endpoint que retorna um objeto do tipo chamada com um id informado")
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
        verificarExistenciaCpfAtendente(chamada.getAtendente());
        verificarExistenciaCpfCliente(chamada.getCliente());
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
    private void verificarExistenciaCpfAtendente(Atendente atendente){
        if (atendenteRepository.findByCpf(atendente.getCpf()) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Atendente não está cadastrado.");
        }
    }

    private void verificarExistenciaCpfCliente(Cliente cliente){
        if (clienteRepository.findByCpf(cliente.getCpf()) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não está cadastrado.");
        }
    }
}