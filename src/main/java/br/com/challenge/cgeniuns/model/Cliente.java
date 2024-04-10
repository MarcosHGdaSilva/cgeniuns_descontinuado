package br.com.challenge.cgeniuns.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Cliente {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotBlank(message = "Campo obrigatório")
    private String nome;

    @NotBlank(message = "Campo obrigatório")
    @Size(min=11,  message = "CPF Inválido")
    private String cpf;

    @NotBlank(message = "Campo obrigatório")
    private String genero;

    @NotBlank(message = "Campo obrigatório")
    private String cep;

    @NotBlank(message = "Campo obrigatório")
    @Size(min = 9, message = "Telefone Inválido")
    private String telefone;

    @NotBlank(message = "Campo obrigatório")
    private String email;

    @Past(message =  "Data de nascimento inválida")
    private LocalDate dataNascimento;

    @NotBlank(message = "Campo obrigatório")
    @Size(min = 6, message = "Mínimo de 6 caracteres")
    private String senha;

}
