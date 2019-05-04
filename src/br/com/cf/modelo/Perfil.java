package br.com.cf.modelo;

public enum Perfil {
    
    ADMINISTRADOR("Administrador"),
    CLIENTE("Cliente");
    
    private String descricao;
    
    Perfil(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
