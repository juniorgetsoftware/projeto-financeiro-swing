package br.com.cf.bo;

import br.com.cf.dao.CategoriaDao;
import br.com.cf.excecao.RegraNegocioException;
import br.com.cf.modelo.Categoria;
import java.util.List;

public class CategoriaBo {

    public CategoriaBo() {
        this.dao = new CategoriaDao();
    }

    private CategoriaDao dao;

    public void salvar(Categoria categoria) throws RegraNegocioException {
        validar(categoria);
        dao.salvar(categoria);
    }

    public void editar(Categoria categoria) throws RegraNegocioException {
        validar(categoria);
        dao.editar(categoria);
    }

    public void deletar(Categoria categoria) {
        dao.deletar(categoria);
    }

    public List<Categoria> listar() {
        return dao.listar();
    }

    private void validar(Categoria categoria) throws RegraNegocioException {
        if (categoria == null) {
            throw new RegraNegocioException("A categoria não pode ser nula!");
        }

        if (categoria.getNome() == null && categoria.getNome().trim().isEmpty()) {
            throw new RegraNegocioException("O nome da categoria é inválido.");
        }

        if (categoria.getNome().length() < 5) {
            throw new RegraNegocioException("O nome da categoria deve ter pelo menos 5 caracteres.");
        }
    }
}
