package br.com.minhaempresa.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "projeto")
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @JsonFormat(pattern="dd/MM/yyyy")
    @Column(name = "data_inicio")
    private Date dataInicio;

    @JsonFormat(pattern="dd/MM/yyyy")
    @Column(name = "data_previsao_fim")
    private Date dataPrevisaoFim;

    @JsonFormat(pattern="dd/MM/yyyy")
    @Column(name = "data_fim")
    private Date dataFim;

    @Column(name = "descricao", length = 5000)
    private String descricao;

    @Column(name = "status", length = 45)
    private StatusProjeto status;

    @Column(name = "orcamento")
    private Float orcamento;

    @Column(name = "risco", length = 45)
    private RiscoProjeto risco;

    @ManyToOne
    @JoinColumn(name = "idgerente", referencedColumnName = "id")
    private Pessoa pessoa;

}
