package br.com.challenge.cgeniuns.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Atendente {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotBlank(message = "Campo obrigatório")
    private String nome_atendente;

    @NotBlank(message = "Campo obrigatório")
    @Size(min=11,  message = "CPF Inválido")
    private String cpf;

    @NotBlank(message = "Campo obrigatório")
    private String setor;

    @NotBlank(message = "Campo obrigatório")
    @Size(min = 6, message = "Mínimo de 6 caracteres")
    private String senha;

    @PositiveOrZero(message = "A avaliação deve ser um número positivo")
    @Max(value = 5, message = "A avaliação deve ser um número entre 0 e 5")
    private Integer avaliacao_atendente;

}
