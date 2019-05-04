package br.com.cf.dao;

import br.com.cf.conexao.Conexao;
import br.com.cf.modelo.Acao;
import br.com.cf.modelo.Auditoria;
import br.com.cf.modelo.Usuario;
import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuditoriaDao {

    private Connection conexao;

    public void salvar(Auditoria auditoria) {
        String sql = "INSERT INTO auditoria (acao, usuario_id, data, registro_antes, registro_depois) values (?, ?, ?, ?, ?)";
        conexao = Conexao.getConexao();
        PreparedStatement ps;
        try {
            ps = this.conexao.prepareStatement(sql);
            ps.setString(1, auditoria.getAcao().toString());
            ps.setLong(2, auditoria.getUsuario().getId());
            ps.setTimestamp(3, new Timestamp(auditoria.getData().getTime()));
            ps.setString(4, auditoria.getRegistroAntes());
            ps.setString(5, auditoria.getRegistroDepois());

            ps.execute();
            ps.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(AuditoriaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Auditoria> listar() {
        String sql = "select auditoria.*, usuario.id, usuario.nome from auditoria inner join usuario on auditoria.usuario_id = usuario.id";
        List<Auditoria> auditoria = new ArrayList<>();
        try {
            conexao = Conexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Usuario usuario = new Usuario();
                usuario.setId(rs.getLong("usuario.id"));
                usuario.setNome(rs.getString("usuario.nome"));

                Auditoria a = new Auditoria();
                a.setId(rs.getLong("auditoria.id"));
                a.setAcao(Enum.valueOf(Acao.class, rs.getString("acao")));
                a.setData(rs.getTimestamp("data"));
                a.setRegistroAntes(rs.getString("registro_antes"));
                a.setRegistroDepois(rs.getString("registro_depois"));
                a.setUsuario(usuario);

                auditoria.add(a);
            }
            rs.close();
            ps.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(ContaDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return auditoria;
    }

}
