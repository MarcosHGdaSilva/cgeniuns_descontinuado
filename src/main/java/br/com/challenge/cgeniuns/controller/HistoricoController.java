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
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de históricos retornada com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public List<Historico> index(){
        return  historicoRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Cria um novo histórico.", description = "Endpoint para criar um novo histórico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Histórico cadastrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro de validação do histórico"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public Historico create(@RequestBody Historico historico){
        log.info("cadastrando historico: {}", historico);
        verificarExistenciaCpfCliente(historico.getCpfCliente());
        return  historicoRepository.save(historico);
    }

    @GetMapping("{id}")
    @Operation(summary = "Retorna um historico especifico cadastrado no sistema.", description = "Endpoint que retorna um objeto do tipo historico com um id informado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Histórico encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Histórico não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Historico> get(@PathVariable Long id){
        log.info("Buscar por id: {}", id);
        return  historicoRepository
        .findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("cpf/{cpfCliente}")
    @Operation(summary = "Retorna um historico especifico cadastrado no sistema.", description = "Endpoint que retorna um objeto do tipo historico com um cpf informado de um cliente relacionado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Histórico encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Histórico não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
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
    @Operation(summary = "Deleta um histórico pelo ID.", description = "Endpoint que deleta um histórico com um ID informado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Histórico deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Histórico não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public void destroy (@PathVariable Long id){
        log.info("Apagando id {}", id);
        verificarExistencia(id);
        historicoRepository.deleteById(id);
    }

    @PutMapping("{id}")
    @Operation(summary = "Atualiza um histórico pelo ID.", description = "Endpoint que atualiza um histórico com um ID informado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Histórico atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro de validação do histórico"),
        @ApiResponse(responseCode = "404", description = "Histórico não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public Historico update(@PathVariable Long id, @RequestBody Historico historico){
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
