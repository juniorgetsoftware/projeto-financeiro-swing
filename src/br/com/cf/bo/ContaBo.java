package br.com.cf.bo;

import br.com.cf.dao.ContaDao;
import br.com.cf.excecao.RegraNegocioException;
import br.com.cf.modelo.Conta;
import java.util.List;

public class ContaBo {

    public ContaBo() {
        this.dao = new ContaDao();
    }

    private ContaDao dao;

    public void salvar(Conta conta) throws RegraNegocioException {
        validar(conta);
        dao.salvar(conta);
    }

    public void editar(Conta conta) throws RegraNegocioException {
        validar(conta);
        dao.editar(conta);
    }

    public void deletar(Conta conta) {
        dao.deletar(conta);
    }

    public List<Conta> listar() {
        return dao.listar();
    }

    private void validar(Conta conta) throws RegraNegocioException {

    }
}
