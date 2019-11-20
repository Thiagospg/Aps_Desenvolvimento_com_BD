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
}
