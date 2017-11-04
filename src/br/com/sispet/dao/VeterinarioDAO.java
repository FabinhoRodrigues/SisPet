package br.com.sispet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.sispet.factory.ConnectionFactory;
import br.com.sispet.modelo.Veterinario;

/**
 *
 * @author gabriel.almeida
 */
public class VeterinarioDAO {
//	private String jdbcURL;
//	private String jdbcUserName;
//	private String jdbcPassword;
	
	private Connection conexao;

//	public VeterinarioDAO(String jdbcURL, String jdbcUserName, String jdbcPassword) {
//		this.jdbcURL = jdbcURL;
//		this.jdbcUserName = jdbcUserName;
//		this.jdbcPassword = jdbcPassword;
//		this.jdbcConnection = jdbcConnection;
//	}

//	protected void connect() throws SQLException {
//		if (jdbcConnection == null || jdbcConnection.isClosed()) {
//			try {
//				Class.forName("com.mysql.jdbc.Driver");
//			} catch (ClassNotFoundException e) {
//				throw new SQLException(e);
//			}
//			jdbcConnection = (Connection) DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
//		}
//	}

//	protected void disconnect() throws SQLException {
//		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
//			jdbcConnection.close();
//		}
//	}

	public boolean insereVeterinario(Veterinario veterinario) {
		
		try{
			conexao = new ConnectionFactory().getConnection();
			
			String sql = "INSERT INTO veterinario(nome, sobrenome, cpf, usuario, senha) VALUES (?, ?, ?, ?, ?)";
	
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setString(1, veterinario.getNome());
			statement.setString(2, veterinario.getSobrenome());
			statement.setString(3, veterinario.getCpf());
			statement.setString(4, veterinario.getUsuario());
			statement.setString(5, veterinario.getSenha());
	
			boolean rowInserted = statement.executeUpdate() > 0;
			statement.close();
	
			return rowInserted;
		} catch(SQLException e){
			
		} finally {
			try {
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public List<Veterinario> listaTodosVeterinarios() throws SQLException {
		Statement statement = null;
		ResultSet resultSet = null;
		
		try{
			conexao = new ConnectionFactory().getConnection();
	
			String sql = "SELECT * FROM veterinario";
	
			statement = conexao.createStatement();
			resultSet = statement.executeQuery(sql);
	
			List<Veterinario> listaVeterinario = new ArrayList<>();
			while (resultSet.next()) {
				String nome = resultSet.getString("nome");
				String sobrenome = resultSet.getString("sobrenome");
				String cpf = resultSet.getString("cpf");
	
//				Veterinario veterinario = new Veterinario(nome, sobrenome, cpf);
				Veterinario veterinario = new Veterinario();
				veterinario.setNome(nome);
				veterinario.setSobrenome(sobrenome);
				veterinario.setCpf(cpf);
				listaVeterinario.add(veterinario);
			}

			return listaVeterinario;
		} catch(SQLException e){
			
		} finally {
			conexao.close();
			resultSet.close();
			statement.close();
		}
		return null;
	}

	public boolean deletaVeterinario(Veterinario veterinario) throws SQLException {
		try{
			conexao = new ConnectionFactory().getConnection();
			
			String sql = "DELETE FROM veterinario where cpf = ?";
	
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setString(1, veterinario.getCpf());
	
			boolean rowDeleted = statement.executeUpdate() > 0;
			statement.close();

			return rowDeleted;
		} catch(SQLException e){
			
		} finally {
			conexao.close();
		}
		return false;
	}

	public boolean updateVeterinario(Veterinario veterinario) throws SQLException {
		try{
			conexao = new ConnectionFactory().getConnection();
			
			String sql = "UPDATE veterinario SET nome=?, sobrenome=?";
			sql += "WHERE cpf = ?";
	
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setString(1, veterinario.getNome());
			statement.setString(2, veterinario.getSobrenome());
	
			boolean rowUpdate = statement.executeUpdate() > 0;
			statement.close();

			return rowUpdate;
		} catch(SQLException e){
			
		} finally {
			conexao.close();
		}
		return false;
	}

	public Veterinario getVeterinario(int id) {
		try{
			conexao = new ConnectionFactory().getConnection();
			
			Veterinario veterinario = null;
			String sql = "SELECT * FROM veterinario WHERE cpf = ?";
		} catch(Exception e){
			
		} finally {
			try {
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		

		return null;
	}

}
