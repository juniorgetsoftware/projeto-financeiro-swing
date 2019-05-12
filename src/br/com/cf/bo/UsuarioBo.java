package br.com.cf.bo;

import br.com.cf.dao.UsuarioDao;
import br.com.cf.excecao.RegraNegocioException;
import br.com.cf.modelo.Usuario;
import java.util.List;

public class UsuarioBo {

    public UsuarioBo() {
        this.dao = new UsuarioDao();
    }

    private UsuarioDao dao;

    public void salvar(Usuario usuario) throws RegraNegocioException {
        validar(usuario);
        dao.salvar(usuario);
    }

    public void editar(Usuario usuario) throws RegraNegocioException {
        validar(usuario);
        dao.editar(usuario);
    }

    public void deletar(Usuario usuario) {
        dao.deletar(usuario);
    }

    public List<Usuario> listar() {
        return dao.listar();
    }

    private void validar(Usuario usuario) throws RegraNegocioException {

    }
}
