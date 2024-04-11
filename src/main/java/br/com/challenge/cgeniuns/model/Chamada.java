package br.com.challenge.cgeniuns.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class Chamada {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate dt_chamada;

    private LocalTime hora;

    private LocalTime duracao;

    private boolean resultado;

    @NotBlank(message="Campo Obrigatório")
    private String cpf_user;

    @NotBlank(message="Campo Obrigatório")
    private String cpf_atendente;
}
