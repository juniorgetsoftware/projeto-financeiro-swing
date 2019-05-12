package br.com.cf.bo;

import br.com.cf.dao.AuditoriaDao;
import br.com.cf.excecao.RegraNegocioException;
import br.com.cf.modelo.Auditoria;
import java.util.List;

public class AuditoriaBo {

    public AuditoriaBo() {
        this.dao = new AuditoriaDao();
    }

    private AuditoriaDao dao;

    public void salvar(Auditoria auditoria) throws RegraNegocioException {
        validar(auditoria);
        dao.salvar(auditoria);
    }

    public List<Auditoria> listar() {
        return dao.listar();
    }

    private void validar(Auditoria auditoria) throws RegraNegocioException {

    }
}
