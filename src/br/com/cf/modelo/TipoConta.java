package br.com.cf.modelo;

public enum TipoConta {
    
    RECEITA("Receita"),
    DESPESA("Despesa");
    
    private String descricao;
    
    TipoConta(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
