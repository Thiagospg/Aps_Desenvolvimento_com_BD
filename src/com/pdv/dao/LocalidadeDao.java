package com.pdv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LocalidadeDao {

	public ResultSet getLocalidades() throws ClassNotFoundException, SQLException {
		Connection con = ConexaoBd.conectar();

		PreparedStatement stmt = con.prepareStatement("SELECT codlocal FROM Localidade");

		ResultSet rs = stmt.executeQuery();

		return rs;
	}
}
