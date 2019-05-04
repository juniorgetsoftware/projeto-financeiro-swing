package br.com.cf.modelo;

public enum Status {

    ATIVO("Ativo"),
    INATIVO("Inativo");

    Status(String descricao) {
        this.descricao = descricao;
    }

    private String descricao;

    public String getDescricao() {
        return descricao;
    }
}
