package br.com.minhaempresa.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@IdClass(MembroId.class)
@Table(name = "membros")
public class Membro {

    @Id
    @ManyToOne
    @JoinColumn(name = "idprojeto")
    private Projeto projeto;

    @Id
    @ManyToOne
    @JoinColumn(name = "idpessoa")
    private Pessoa pessoa;

}




