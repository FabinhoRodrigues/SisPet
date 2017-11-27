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
import br.com.sispet.utils.Utils;

/**
 *
 * @author gabriel.almeida
 */
public class VeterinarioDAO {
	
	private Connection conexao;

	public boolean insereVeterinario(Veterinario veterinario) {
		PreparedStatement ps = null;
		boolean sucesso = false;
		try{
			conexao = new ConnectionFactory().getConnection();
			
			conexao.setAutoCommit(false);
			
			String sql = "INSERT INTO usuario(nome, usuario, senha, perfil, data_cadastro) VALUES (?, ?, ?, ?, ?)";
			ps = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, veterinario.getNome());
			ps.setString(2, veterinario.getUsuario());
			ps.setString(3, Utils.criptografarSenha(veterinario.getSenha()));
			ps.setString(4, "VET");
			String dataCadastro = Utils.getDataHoraCorrente(); 
			ps.setString(5, dataCadastro);
			
			ps.execute();
			sucesso = true;
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int id_usuario = rs.getInt(1);
			rs.close();
			
			sql = "INSERT INTO veterinario(id_usuario, email, especialidade, cpf, telefone) VALUES (?, ?, ?, ?, ?)";
	
			ps = conexao.prepareStatement(sql);
			ps.setLong(1, id_usuario);
			ps.setString(2, veterinario.getEmail());
			ps.setString(3, veterinario.getEspecialidade());
			ps.setString(4, veterinario.getCpf());
			ps.setString(5, veterinario.getTelefone());
			
			ps.execute();
			sucesso = true;
			
			conexao.commit();
	
			return sucesso;
		} catch(SQLException e){
			System.out.println("Erro ao cadastrar o veterinario.");
			if (conexao != null){
				try { 
					System.out.println("Rollback efetuado na transação");
					conexao.rollback();
					sucesso = false;
					System.out.println("Código de Erro: " + e.getErrorCode() + "  Mensagem de Erro =  " + e.getMessage());
				} catch(SQLException e2) {
					System.err.print("Erro na transação!" + e2); 
				} 
			}
		} finally {
			try {
				ps.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sucesso;
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
				String cpf = resultSet.getString("cpf");
	
//				Veterinario veterinario = new Veterinario(nome, sobrenome, cpf);
				Veterinario veterinario = new Veterinario();
				veterinario.setNome(nome);
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

	public boolean alterarVeterinario(Veterinario veterinario) {
		PreparedStatement ps = null;
		boolean sucesso = false;

		try{
			conexao = new ConnectionFactory().getConnection();
			conexao.setAutoCommit(false);
			
			String sql = "UPDATE usuario SET nome = ?, usuario = ?, senha = ? WHERE id = ?";
			ps = conexao.prepareStatement(sql);
			ps.setString(1, veterinario.getNome());
			ps.setString(2, veterinario.getUsuario());
			ps.setString(3, Utils.criptografarSenha(veterinario.getSenha()));
			ps.setLong(4, veterinario.getId());
			
			ps.execute();
			sucesso = true;
			
			sql = "UPDATE veterinario SET email = ?, especialidade = ?, cpf = ?, telefone = ? WHERE = id_usuario = ?";
	
			ps = conexao.prepareStatement(sql);
			ps.setString(1, veterinario.getEmail());
			ps.setString(2, veterinario.getEspecialidade());
			ps.setString(3, veterinario.getCpf());
			ps.setString(4, veterinario.getTelefone());
			ps.setLong(5, veterinario.getId());
			
			ps.execute();
			sucesso = true;

			conexao.commit();
	
			return sucesso;
		} catch(SQLException e){
			System.out.println("Erro ao alterar o veterinario.");
			if (conexao != null){
				try { 
					System.out.println("Rollback efetuado na transação");
					conexao.rollback();
					sucesso = false;
					System.out.println("Código de Erro: " + e.getErrorCode() + "  Mensagem de Erro =  " + e.getMessage());
				} catch(SQLException e2) {
					System.err.print("Erro na transação!" + e2); 
				} 
			}
		} finally {
			try {
				ps.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sucesso;
	}

}
