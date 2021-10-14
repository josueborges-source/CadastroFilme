package br.com.cadastrofilme.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;

import br.com.cadastrofilme.model.Filme;

import java.sql.*;

public class FilmeDao {

	private	Connection conexao;
	
	public	FilmeDao() {
			this.conexao = new ConnectionFactory().getConnection();
	}	
	
	public void adiciona(Filme filme) 
	{
	 String	sql	=	"insert	into produtos	"	+
					"(nome, descricao, quantidade, preco, dataAlteracao, dataCriacao)"	+
					"values	(?,?,?,?,?,?)";
	
	try	{
		// Prepared Statement para Insercao
		PreparedStatement stmt = conexao.prepareStatement(sql);
		
		// Definicao de valores
		stmt.setString (1, filme.getNome());
		stmt.setString(2, filme.getDescricao());
		
		/*
		 * stmt.setLong(3, filme.getQuantidade()); stmt.setBigDecimal( 4,
		 * filme.getPreco()); stmt.setDate(5, Date.valueOf(filme.getDataAlteracao()));
		 * stmt.setDate(6,Date.valueOf(filme.getDataCriacao()));
		 */
		
		//Execução
		stmt.execute();
		stmt.close();
		
}	catch	(SQLException	e)	{
		throw new	RuntimeException(e);
}
}

	
}
