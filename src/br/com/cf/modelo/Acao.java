package br.com.cf.modelo;

public enum Acao {

    LOGIN("Login"),
    LOGOUT("Logout"),
    CRIAR("Criar"),
    ALTERAR("Alterar"),
    LISTAR("Listar"),
    EXCLUIR("Excluir");

    private String descricao;

    Acao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
