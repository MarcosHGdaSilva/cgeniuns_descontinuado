package br.com.challenge.cgeniuns.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Chamada {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message="Campo Obrigatório")
    @PastOrPresent(message="hora inválida")
    private LocalDate dtChamada;

    @NotNull(message="Campo Obrigatório")
    private LocalTime hora;

    @NotNull(message="Campo Obrigatório")
    private LocalTime duracao;

    private boolean resultado;

    @ManyToOne
    private Atendente atendente;
    @ManyToOne
    private Cliente cliente;
}
