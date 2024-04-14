package br.com.challenge.cgeniuns.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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

import br.com.challenge.cgeniuns.model.Cliente;
import br.com.challenge.cgeniuns.repository.ClienteRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("cliente")
@Slf4j
public class ClienteController {
    
    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> index(){
        return  clienteRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Cliente create(@RequestBody Cliente cliente){
        log.info("cadastrando cliente: {}", cliente);
        if (clienteRepository.findByCpf_cliente(cliente.getCpf_cliente()) == null){
            return clienteRepository.save(cliente);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente já cadastrado");
        }
        
    }

    @GetMapping("{id}")
    public ResponseEntity<Cliente> get(@PathVariable Long id){
        log.info("Buscar por id: {}", id);
        return clienteRepository
        .findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("cpf/{cpf}")
    public ResponseEntity<Cliente> get(@PathVariable String cpf_cliente){
        log.info("Buscar por CPF: {}", cpf_cliente);
        Cliente cliente = clienteRepository.findByCpf_cliente(cpf_cliente);
    if (cliente != null) {
        return ResponseEntity.ok(cliente);
    } else {
        return ResponseEntity.notFound().build();
    }
    }
    
    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void destroy (@PathVariable Long id){
        log.info("Apagando id {}", id);
        verificarId(id);
        clienteRepository.deleteById(id);
    }

    @Transactional
    @DeleteMapping("cpf/{cpf}")
    @ResponseStatus(NO_CONTENT)
    public void deleteByCpf_cliente (@PathVariable String cpf_cliente){
        log.info("Apagando Cliente com CPF {}", cpf_cliente);
        verificarCpf(cpf_cliente);
        clienteRepository.deleteByCpf_cliente(cpf_cliente);
    }

    @PutMapping("{id}")
    public  Cliente update(@PathVariable Long id, @RequestBody Cliente cliente){
        log.info("Atualizando o cadastro do id={} para {}", id, cliente);
        verificarId(id);
        cliente.setId(id);
        return clienteRepository.save(cliente);
    }

    @PutMapping("cpf/{cpf}")
    public  Cliente update(@PathVariable String cpf_cliente, @RequestBody Cliente cliente){
        log.info("Atualizando o cadastro do pcf={} para {}", cpf_cliente, cliente);
        verificarCpf(cpf_cliente);
        cliente.setCpf_cliente(cpf_cliente);
        return clienteRepository.save(cliente);
    }

    private void verificarId(Long id){
        clienteRepository.
        findById(id)
        .orElseThrow(
            ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "id não encontrado")
        );
    }
    private void verificarCpf(String cpf_cliente){
        Cliente cliente = clienteRepository.findByCpf_cliente(cpf_cliente);
    if (cliente == null) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente com CPF não encontrado");
    }
}
}
