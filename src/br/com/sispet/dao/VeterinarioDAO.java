package br.com.sispet.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

import br.com.sispet.modelo.Veterinario;

/**
 *
 * @author gabriel.almeida
 */
public class VeterinarioDAO {
	private String jdbcURL;
	private String jdbcUserName;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public VeterinarioDAO(String jdbcURL, String jdbcUserName, String jdbcPassword, Connection jdbcConnection) {
		this.jdbcURL = jdbcURL;
		this.jdbcUserName = jdbcUserName;
		this.jdbcPassword = jdbcPassword;
		this.jdbcConnection = jdbcConnection;
	}

	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = (Connection) DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
		}
	}

	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}

	public boolean insereVeterinario(Veterinario veterinario) throws SQLException {
		String sql = "INSERT INTO veterinario(nome, sobrenome, cpf, usuario, senha) VALUES (?, ?, ?, ?, ?)";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, veterinario.getNome());
		statement.setString(2, veterinario.getSobrenome());
		statement.setInt(3, veterinario.getCpf());
		statement.setString(4, veterinario.getUsuario());
		statement.setString(5, veterinario.getSenha());

		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;
	}

	public List<Veterinario> listaTodosVeterinarios() throws SQLException {
		List<Veterinario> listaVeterinario = new ArrayList<>();

		String sql = "SELECT * FROM veterinario";

		connect();

		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			String nome = resultSet.getString("nome");
			String sobrenome = resultSet.getString("sobrenome");
			int cpf = resultSet.getInt("cpf");

			Veterinario veterinario = new Veterinario(nome, sobrenome, cpf);
			listaVeterinario.add(veterinario);
		}

		resultSet.close();
		statement.close();

		disconnect();

		return listaVeterinario;
	}

	public boolean deletaVeterinario(Veterinario veterinario) throws SQLException {
		String sql = "DELETE FROM veterinario where cpf = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, veterinario.getCpf());

		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();

		return rowDeleted;
	}

	public boolean updateVeterinario(Veterinario veterinario) throws SQLException {
		String sql = "UPDATE veterinario SET nome=?, sobrenome=?";
		sql += "WHERE cpf = ?";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, veterinario.getNome());
		statement.setString(2, veterinario.getSobrenome());

		boolean rowUpdate = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdate;
	}

	public Veterinario getVeterinario(int id) throws SQLException {
		Veterinario veterinario = null;
		String sql = "SELECT * FROM veterinario WHERE cpf = ?";
		connect();

		return null;
	}

}
