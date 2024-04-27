package br.com.challenge.cgeniuns.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Script {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message="Campo Obrigatório")
    private Long id_compra;

    @NotNull(message="Campo Obrigatório")
    private Long id_produto;
    
    @NotNull(message="Campo Obrigatório")
    private Long id_chamada;
    
    @NotBlank(message="Campo Obrigatório")
    private String cpf_cliente;

    @NotBlank(message="Campo Obrigatório")
    private String descricao_script;
}
