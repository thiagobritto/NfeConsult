package com.github.thiagobritto.nfeconsult.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	private static Connection CONEXAO;
	
	private Conexao() {
		try {
			if(CONEXAO == null || CONEXAO.isClosed()) {
				String url = "jdbc:sqlite:src/main/java/com/github/thiagobritto/nfeconsult/resources/files/database.db";
				CONEXAO = DriverManager.getConnection(url);
			}			
		} catch (SQLException e) {
			System.err.println("Houve ao abrir a conexao com banco!");
			e.printStackTrace();
		}
	}
	
	public static Conexao getInstance() {
		return new Conexao();			
	}
	
	public Connection getConnection() {
		return CONEXAO;
	}
	
	public void closeConnection() {
		try {
			CONEXAO.close();
		} catch (SQLException e) {
			System.err.println("Houve um erro ao fechar a conexao com banco!");
			e.printStackTrace();
		}
	}
	
}
