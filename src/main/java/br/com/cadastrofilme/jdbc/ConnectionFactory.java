package br.com.cadastrofilme.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	String linkDeConexao = "jdbc:mysql://localhost/dbAtividade?useTimezone=true&serverTimezone=UTC";
	String login = "root";
	String senha = "root.2302";

	public	Connection	getConnection() {
		try	{
		return DriverManager.getConnection(
				linkDeConexao, login, senha);
		}	
		catch (SQLException	e)	
		{
		throw new RuntimeException(e);
		}
	}


	
}
