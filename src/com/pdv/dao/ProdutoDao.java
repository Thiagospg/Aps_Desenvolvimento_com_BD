package com.pdv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdutoDao {

	public ResultSet getProdutos() throws ClassNotFoundException, SQLException {
		Connection con = ConexaoBd.conectar();

		PreparedStatement stmt = con.prepareStatement("SELECT codprod FROM Produto");

		ResultSet rs = stmt.executeQuery();

		return rs;
	}

	public ResultSet getDescricaoById(int id) throws ClassNotFoundException, SQLException {
		Connection con = ConexaoBd.conectar();

		PreparedStatement stmt = con.prepareStatement("SELECT descricao FROM Produto WHERE codprod = ?");

		stmt.setInt(1, id);

		ResultSet rs = stmt.executeQuery();

		return rs;
	}

	public String[] getDescPrecoById(Integer id) throws ClassNotFoundException, SQLException {
		Connection con = ConexaoBd.conectar();

		PreparedStatement stmt = con.prepareStatement("SELECT descricao, precounitario FROM Produto WHERE codprod = ?");
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();

		String[] vetorDados = new String[2];

		while (rs.next()) {
			vetorDados[0] = rs.getString("descricao");
			vetorDados[1] = String.valueOf(rs.getDouble("precounitario"));
		}

		return vetorDados;

	}

	public Integer getQuantidadeById(Integer id) throws ClassNotFoundException, SQLException {
		Connection con = ConexaoBd.conectar();

		PreparedStatement stmt = con.prepareStatement("SELECT qtdestoque FROM Produto WHERE codprod = ?");
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();

		int quantidade = 0;
		while (rs.next()) {
			quantidade = rs.getInt("qtdestoque");
		}
		return quantidade;

	}

	public Integer updateQuantidadeById(Integer id, Integer quantidadeComprada)
			throws ClassNotFoundException, SQLException {
		Connection con = ConexaoBd.conectar();

		Integer qtdAtual = getQuantidadeById(id);

		PreparedStatement stmt = con.prepareStatement("UPDATE Produto SET qtdestoque = ? WHERE codprod = ?");

		stmt.setInt(1, qtdAtual - quantidadeComprada);
		stmt.setInt(2, id);

		return stmt.executeUpdate();
	}

	public boolean checkVenda(Integer id, Integer quantidadeComprada) throws ClassNotFoundException, SQLException {
		Integer qtdAtual = getQuantidadeById(id);

		if (qtdAtual < quantidadeComprada) {
			return false;
		}
		return true;
	}

	public String vender(Integer id, Integer quantidadeComprada) throws ClassNotFoundException, SQLException {
		if (checkVenda(id, quantidadeComprada)) {
			updateQuantidadeById(id, quantidadeComprada);
			return "Produto adicionado!";
		} else {
			return "Produto sem estoque!";
		}
	}
}
