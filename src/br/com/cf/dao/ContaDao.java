package br.com.cf.dao;

import br.com.cf.conexao.Conexao;
import br.com.cf.modelo.Categoria;
import br.com.cf.modelo.Conta;
import br.com.cf.modelo.Status;
import br.com.cf.modelo.TipoConta;
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

    public void salvar(Conta conta) {
        String sql = "INSERT INTO controle_financeiro.conta (descricao, valor, categoria_id, tipo, data_pagamento, data_vencimento, status) VALUES(?, ?, ?, ?, ?, ?, ?);";
        conexao = Conexao.getConexao();
        PreparedStatement ps;
        try {
            ps = this.conexao.prepareStatement(sql);

            ps.setString(1, conta.getDescricao());
            ps.setBigDecimal(2, conta.getValor());
            ps.setLong(3, conta.getCategoria().getId());
            ps.setString(4, conta.getTipoConta().toString());
            ps.setDate(5, conta.getDataPagamento() != null ? new java.sql.Date(conta.getDataPagamento().getTime()) : null);
            ps.setDate(6, new java.sql.Date(conta.getDataVencimento().getTime()));
            ps.setString(7, conta.getStatus().toString());

            ps.execute();
            ps.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(ContaDao.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public void editar(Conta conta) {
        String sql = "UPDATE conta SET descricao=?, valor=?, categoria_id=?, tipo=?, data_pagamento=?, data_vencimento=?, status=? WHERE id=?;";
        conexao = Conexao.getConexao();
        PreparedStatement ps;
        try {
            ps = this.conexao.prepareStatement(sql);
            ps.setString(1, conta.getDescricao());
            ps.setBigDecimal(2, conta.getValor());
            ps.setLong(3, conta.getCategoria().getId());
            ps.setString(4, conta.getTipoConta().toString());
            ps.setDate(5, new java.sql.Date(conta.getDataPagamento().getTime()));
            ps.setDate(6, new java.sql.Date(conta.getDataVencimento().getTime()));
            ps.setString(7, conta.getStatus().toString());
            ps.setLong(8, conta.getId());

            ps.execute();
            ps.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(ContaDao.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public void deletar(Conta conta) {
        String sql = "delete from conta where id = ?";
        conexao = Conexao.getConexao();
        PreparedStatement ps;
        try {
            ps = this.conexao.prepareStatement(sql);
            ps.setLong(1, conta.getId());
            ps.execute();
            ps.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(ContaDao.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public List<Conta> listar() {
        String sql = "select conta.*, categoria.* from conta  inner join categoria on conta.categoria_id = categoria.id";
        List<Conta> contas = new ArrayList<>();
        try {
            conexao = Conexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Conta conta = new Conta();

                conta.setCategoria(new Categoria(rs.getLong("categoria.id"), rs.getString("categoria.nome")));
                conta.setDataPagamento(rs.getDate("conta.data_pagamento"));
                conta.setDataVencimento(rs.getDate("conta.data_vencimento"));
                conta.setDescricao(rs.getString("descricao"));
                conta.setId(rs.getLong("conta.id"));
                conta.setTipoConta(Enum.valueOf(TipoConta.class, rs.getString("conta.tipo")));
                conta.setValor(rs.getBigDecimal("valor"));
                conta.setStatus(Enum.valueOf(Status.class, rs.getString("conta.status")));

                contas.add(conta);
            }
            rs.close();
            ps.close();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(ContaDao.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }

        return contas;
    }
}
