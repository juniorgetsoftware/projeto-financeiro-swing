package br.com.cf.modelo;

import java.util.Date;
import java.util.Objects;

public class Auditoria {

    private Long id;
    private Acao acao;
    private Usuario usuario;
    private Date data;
    private String registroAntes;
    private String registroDepois;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Acao getAcao() {
        return acao;
    }

    public void setAcao(Acao acao) {
        this.acao = acao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getRegistroAntes() {
        return registroAntes;
    }

    public void setRegistroAntes(String registroAntes) {
        this.registroAntes = registroAntes;
    }

    public String getRegistroDepois() {
        return registroDepois;
    }

    public void setRegistroDepois(String registroDepois) {
        this.registroDepois = registroDepois;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Auditoria other = (Auditoria) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Auditoria{" + "id=" + id + ", acao=" + acao + ", usuario=" + usuario + ", data=" + data + ", registroAntes=" + registroAntes + ", registroDepois=" + registroDepois + '}';
    }

}
