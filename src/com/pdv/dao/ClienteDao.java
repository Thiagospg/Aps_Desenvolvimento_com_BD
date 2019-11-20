package com.pdv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDao {

	public ResultSet getClientes() throws ClassNotFoundException, SQLException {
		Connection con = ConexaoBd.conectar();

		PreparedStatement stmt = con.prepareStatement("SELECT codcli FROM Cliente");

		ResultSet rs = stmt.executeQuery();

		return rs;
	}
}
