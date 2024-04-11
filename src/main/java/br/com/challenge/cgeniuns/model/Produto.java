package br.com.challenge.cgeniuns.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Entity
public class Produto {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotBlank(message = "Campo Obrigatório")
    private String descricao;
    
    @NotBlank(message = "Campo Obrigatório")
    private String nome;
    
    @Positive
    private BigDecimal valor;
    
}
