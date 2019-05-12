package br.com.cf.dao;

import br.com.cf.conexao.Conexao;
import br.com.cf.modelo.Perfil;
import br.com.cf.modelo.Usuario;
import br.com.cf.modelo.Status;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDao {

    private Connection conexao;

    public void salvar(Usuario usuario) {
        String sql = "INSERT INTO usuario (nome, perfil, senha, login, telefone, email, status) VALUES(?, ?, ?, ?, ?, ?, ?)";
        conexao = Conexao.getConexao();
        PreparedStatement ps;
        try {
            ps = this.conexao.prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getPerfil().toString());
            ps.setString(3, usuario.getSenha());
            ps.setString(4, usuario.getLogin());
            ps.setString(5, usuario.getTelefone());
            ps.setString(6, usuario.getEmail());
            ps.setString(7, usuario.getStatus().toString());
            ps.execute();
            ps.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editar(Usuario usuario) {
        String sql = "UPDATE usuario SET nome=?, perfil=?, senha=?, login=?, telefone=?, email=?, status=? WHERE id=?";
        conexao = Conexao.getConexao();
        PreparedStatement ps;
        try {
            ps = this.conexao.prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getPerfil().toString());
            ps.setString(3, usuario.getSenha());
            ps.setString(4, usuario.getLogin());
            ps.setString(5, usuario.getTelefone());
            ps.setString(6, usuario.getEmail());
            ps.setString(7, usuario.getStatus().toString());
            ps.setLong(8, usuario.getId());

            ps.execute();
            ps.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deletar(Usuario usuario) {
        String sql = "delete from usuario where id = ?";
        conexao = Conexao.getConexao();
        PreparedStatement ps;
        try {
            ps = this.conexao.prepareStatement(sql);
            ps.setLong(1, usuario.getId());
            ps.execute();
            ps.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Usuario> listar() {
        String sql = "select * from usuario";
        List<Usuario> usuarios = new ArrayList<>();
        try {
            conexao = Conexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setEmail(rs.getString("email"));
                usuario.setId(rs.getLong("id"));
                usuario.setLogin(rs.getString("login"));
                usuario.setNome(rs.getString("nome"));
                usuario.setPerfil(Enum.valueOf(Perfil.class, rs.getString("perfil")));
                usuario.setSenha(rs.getString("senha"));
                usuario.setStatus(Enum.valueOf(Status.class, rs.getString("status")));
                usuario.setTelefone(rs.getString("telefone"));

                usuarios.add(usuario);
            }
            rs.close();
            ps.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return usuarios;
    }
}
