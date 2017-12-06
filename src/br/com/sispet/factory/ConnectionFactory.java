package br.com.sispet.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private static final String URL = "jdbc:mysql://localhost/sispet";
	private static final String USER = "root";
	private static final String PASS = "root";

	public Connection getConnection(){
		try{
			return DriverManager.getConnection(URL, USER, PASS);
		} catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
}
