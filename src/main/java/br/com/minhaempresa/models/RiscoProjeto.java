package br.com.minhaempresa.models;

public enum RiscoProjeto {
    BAIXO("Baixo"),
    MEDIO("Médio"),
    ALTO("Alto");

    private String descricao;

    RiscoProjeto(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

