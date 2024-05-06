package br.com.challenge.cgeniuns.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotBlank(message = "Campo Obrigatório")
    private String nome_produto;

    @NotBlank(message = "Campo Obrigatório")
    private String descricao_produto;
    
    @PositiveOrZero(message = "O valor do produto deve ser positivo")
    private BigDecimal valor_produto;

    @ManyToOne
    private Historico historico;
}
