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
import br.com.sispet.modelo.FiltroDeConsultaAnimal;

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

}
