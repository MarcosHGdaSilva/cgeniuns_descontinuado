package br.com.challenge.cgeniuns.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.challenge.cgeniuns.model.Atendente;
import br.com.challenge.cgeniuns.model.Chamada;
import br.com.challenge.cgeniuns.model.Cliente;
import br.com.challenge.cgeniuns.model.Historico;
import br.com.challenge.cgeniuns.model.Produto;
import br.com.challenge.cgeniuns.model.Script;
import br.com.challenge.cgeniuns.repository.AtendenteRepository;
import br.com.challenge.cgeniuns.repository.ChamadaRepository;
import br.com.challenge.cgeniuns.repository.ClienteRepository;
import br.com.challenge.cgeniuns.repository.HistoricoRepository;
import br.com.challenge.cgeniuns.repository.ProdutoRepository;
import br.com.challenge.cgeniuns.repository.ScriptRepository;

@Configuration
public class DatabaseSeeder implements CommandLineRunner{
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    AtendenteRepository atendenteRepository;
    @Autowired
    ChamadaRepository chamadaRepository;
    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    HistoricoRepository historicoRepository;
    @Autowired
    ScriptRepository scriptRepository;



    @Override
    public void run(String... args) throws Exception {

        clienteRepository.saveAll(
            List.of(
                Cliente.builder()
                .nome_cliente("Gabriel")
                .cpf("12345678910")
                .genero("masculino")
                .cep ("12345678")
                .telefone ("912345678")
                .email ("teste@gmail.com")
                .preferencia_contato ("Manhã")
                .dtNascimento(LocalDate.of(2020,02,03))
                .senha_user ("123456")
                .build()));
        atendenteRepository.saveAll(
            List.of(
                Atendente.builder()
                    .nome_atendente("João")
                    .cpf("32165498710")
                    .setor("vendas")
                    .senha_atendente("654321")
                    .avaliacao_atendente(4)
                    .build(),
                Atendente.builder()
                    .nome_atendente("Pedro")
                    .cpf("32165498711")
                    .setor("vendas")
                    .senha_atendente("654321")
                    .avaliacao_atendente(4)
                    .build()));
        chamadaRepository.saveAll(
            List.of(
                Chamada.builder()
                    .dt_chamada(LocalDate.of(2020,01,05))
                    .hora(LocalTime.of(10,15,30))
                    .duracao(LocalTime.of(00,05,30))
                    .resultado(Boolean.valueOf(true))
                    .cpf_cliente("12345678910")
                    .cpf_atendente("32165498710")
                    .build(),
                Chamada.builder()
                    .dt_chamada(LocalDate.of(2020,01,05))
                    .hora(LocalTime.of(10,15,30))
                    .duracao(LocalTime.of(00,05,30))
                    .resultado(Boolean.valueOf(true))
                    .cpf_cliente("12345678910")
                    .cpf_atendente("32165498711")
                    .build()));
        produtoRepository.saveAll(
            List.of(
                Produto.builder()
                    .descricao_produto("Descrição do Produto X")
                    .nome_produto("produto X")
                    .valor_produto(new BigDecimal(123.45))
                    .build(),
                Produto.builder()
                    .descricao_produto("Descrição do Produto Y")
                    .nome_produto("produto X")
                    .valor_produto(new BigDecimal(3215.45))
                    .build(),
                Produto.builder()
                    .descricao_produto("Descrição do Produto Z")
                    .nome_produto("produto X")
                    .valor_produto(new BigDecimal(9851.45))
                    .build(),
                Produto.builder()
                    .descricao_produto("Descrição do Serviço Ae")
                    .nome_produto("Serviço Ae")
                    .valor_produto(new BigDecimal(1298.45))
                    .build()                
                    ));
        historicoRepository.saveAll(
            List.of(
                Historico.builder()
                    .idProduto(Long.valueOf(1))
                    .cpfCliente("12345678910")
                    .dtCompra(LocalDate.of(2020,01,05))
                    .build()));
        scriptRepository.saveAll(
            List.of(
                Script.builder()
                    .id_compra(Long.valueOf(1))
                    .id_produto(Long.valueOf(1))
                    .id_chamada(Long.valueOf(1))
                    .cpf_cliente("12345678910")
                    .descricao_script("Descrição do Script")
                    .build()));
    }
    
}