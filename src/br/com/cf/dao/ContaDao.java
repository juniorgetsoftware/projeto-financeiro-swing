package br.com.cf.dao;

import br.com.cf.conexao.Conexao;
import br.com.cf.modelo.Categoria;
import br.com.cf.modelo.Status;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContaDao {

    private Connection conexao;

    public void salvar(Categoria categoria) {
        String sql = "insert into categoria( nome, status) values (?, ?)";
        conexao = Conexao.getConexao();
        PreparedStatement ps;
        try {
            ps = this.conexao.prepareStatement(sql);
            ps.setString(1, categoria.getNome());
            ps.setString(2, categoria.getStatus().toString());
            ps.execute();
            ps.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(ContaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editar(Categoria categoria) {
        String sql = "update categoria set nome = ?, status = ? where id = ?";
        conexao = Conexao.getConexao();
        PreparedStatement ps;
        try {
            ps = this.conexao.prepareStatement(sql);
            ps.setString(1, categoria.getNome());
            ps.setString(2, categoria.getStatus().toString());
            ps.setLong(3, categoria.getId());
            ps.execute();
            ps.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(ContaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deletar(Categoria categoria) {
        String sql = "delete from categoria where id = ?";
        conexao = Conexao.getConexao();
        PreparedStatement ps;
        try {
            ps = this.conexao.prepareStatement(sql);
            ps.setLong(1, categoria.getId());
            ps.execute();
            ps.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(ContaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Categoria> listar() {
        String sql = "select * from categoria";
        List<Categoria> categorias = new ArrayList<>();
        try {
            conexao = Conexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getLong("id"));
                categoria.setNome(rs.getString("nome"));
                categoria.setStatus(Enum.valueOf(Status.class, rs.getString("status")));

                categorias.add(categoria);
            }
            rs.close();
            ps.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(ContaDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return categorias;
    }
}
