package br.com.challenge.cgeniuns.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Data;

@Data
@Entity
public class Historico {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long idProduto;

    @NotBlank(message="Campo Obrigatório")
    private String cpfCliente;

    @Past(message = "Campo Obrigatório")
    private LocalDate dataCompra;
    
}
