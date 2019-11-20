package com.pdv.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBd {

	public static Connection conectar() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/aps_app_bd?useTimezone=true&serverTimezone=UTC", "root", "root");
		return con;
	}
}
