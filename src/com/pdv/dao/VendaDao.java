package com.pdv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VendaDao {

	public int addVenda(int codCli, int codProd, int codLocal, int qtdVenda, double valorTotal, String dataVenda) throws ClassNotFoundException, SQLException {
		Connection con = ConexaoBd.conectar();

		PreparedStatement stmt = con.prepareStatement("INSERT INTO Venda (codcli, codprod, codlocal, qtdvenda, valortotal, datavenda) VALUES (?, ?, ?, ?, ?, ?)");
		
		stmt.setInt(1, codCli);
		stmt.setInt(2, codProd);
		stmt.setInt(3, codLocal);
		stmt.setInt(4, qtdVenda);
		stmt.setDouble(5, valorTotal);
		stmt.setString(6, dataVenda);
		
		int linhasAfetadas = stmt.executeUpdate();

		return linhasAfetadas;
	}

}
