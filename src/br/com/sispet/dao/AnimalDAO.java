package br.com.sispet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import br.com.sispet.factory.ConnectionFactory;
import br.com.sispet.modelo.Animal;
import br.com.sispet.modelo.filtro.FiltroDeConsultaAnimal;

public class AnimalDAO {
	
	private Connection conexao;
	
	public List<Animal> listar(FiltroDeConsultaAnimal filtro) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			conexao = new ConnectionFactory().getConnection();
			
			String sqlAnimal = "SELECT * FROM animal WHERE 1 = 1 ";
				
			String queryFinalAnimal = montaWhereDinamicoAnimal(filtro, sqlAnimal);
				
			ps = conexao.prepareStatement(queryFinalAnimal);
			rs = ps.executeQuery();
				
			List<Animal> animais = new ArrayList<>();
			while(rs.next()){
				Animal a = new Animal();
				a.setId(rs.getLong("id"));
				a.setId_cliente(rs.getLong("id_cliente"));
				a.setNome(rs.getString("nome"));
				a.setSexo(rs.getString("sexo"));
				a.setEspecie(rs.getString("especie"));
				a.setRaca(rs.getString("raca"));
				a.setIdade(rs.getInt("idade"));
				a.setPeso(rs.getInt("peso"));
				a.setObservacoes(rs.getString("observacoes"));
				
				animais.add(a);
			}
			
			return animais;
			
		} catch(SQLException e){
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				conexao.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	private String montaWhereDinamicoAnimal(FiltroDeConsultaAnimal filtro, String sql) {
		StringBuilder query = new StringBuilder(sql);
		
		if(StringUtils.isNotBlank(filtro.getNome())){
			query.append("AND nome like '%").append(filtro.getNome()).append("%'");
		} 
		if(StringUtils.isNotBlank(filtro.getSexo())){
			query.append("AND sexo = ").append(filtro.getSexo());
		} 
		if(StringUtils.isNotBlank(filtro.getEspecie())){
			query.append("AND especie like '%").append(filtro.getEspecie()).append("%'");
		} 
		if(StringUtils.isNotBlank(filtro.getRaca())){
			query.append("AND raca like '%").append(filtro.getRaca()).append("%'");
		} 
		if(filtro.getIdade() != null){
			query.append("AND idade like '%").append(filtro.getIdade()).append("%'");
		} 
		
		return query.toString();
	}

	public Animal getAnimalById(Long idAnimal) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Animal animal = null;
		try {
			conexao = new ConnectionFactory().getConnection();

			String sql = "SELECT * FROM animal WHERE id = ? ";

			ps = conexao.prepareStatement(sql);
			ps.setLong(1, idAnimal);

			rs = ps.executeQuery();
			if(rs.next()) {
				animal = new Animal();
				animal.setId(rs.getLong("id"));
				animal.setId_cliente(rs.getLong("id_cliente"));
				animal.setNome(rs.getString("nome"));
				animal.setSexo(rs.getString("sexo"));
				animal.setEspecie(rs.getString("especie"));
				animal.setRaca(rs.getString("raca"));
				animal.setIdade(rs.getInt("idade"));
				animal.setPeso(rs.getInt("peso"));
				animal.setObservacoes(rs.getString("observacoes"));

			}

			return animal;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				conexao.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public int getQuantidadeDeAnimaisDoCliente(long id_cliente) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conexao = new ConnectionFactory().getConnection();

			String sql = "SELECT * FROM animal WHERE id_cliente = ? ";

			ps = conexao.prepareStatement(sql);
			ps.setLong(1, id_cliente);

			rs = ps.executeQuery();
			while(rs.next()) {
				count++;
			}

			return count;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				conexao.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public boolean excluir(Long idAnimal) {
		PreparedStatement ps = null; 
		boolean sucesso = false;
		
		try {
			conexao = new ConnectionFactory().getConnection();
			
			String sql = "DELETE FROM animal WHERE id = ?";
			ps = conexao.prepareStatement(sql);
			ps.setLong(1, idAnimal);
			ps.execute();
			sucesso = true;
			
			return sucesso;
			
		} catch (SQLException e1) {
			System.out.println("Código de Erro: " + e1.getErrorCode() + "Mensagem de Erro =  " + e1.getMessage());
		} finally {
			try {
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sucesso;
	}
}
