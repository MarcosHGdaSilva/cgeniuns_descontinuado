package br.com.challenge.cgeniuns.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class Script {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long id_compra;
    
    private Long id_chamada;
    
    @NotBlank(message="Campo Obrigatório")
    private String cpf_user;
}
