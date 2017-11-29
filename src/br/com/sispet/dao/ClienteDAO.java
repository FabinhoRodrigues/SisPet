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
import br.com.sispet.modelo.Cliente;

public class ClienteDAO {

	private Connection conexao;

	public List<Cliente> listar(Cliente cliente) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			conexao = new ConnectionFactory().getConnection();
			
			String sql = "SELECT * FROM cliente WHERE 1 = 1 ";
			
			String queryFinal = montaWhereDinamicoCliente(cliente, sql);
			
			ps = conexao.prepareStatement(queryFinal);
			rs = ps.executeQuery();
			
			List<Cliente> clientes = new ArrayList<>();
			while(rs.next()){
				Cliente c = new Cliente();
				c.setId(rs.getLong("id"));
				c.setNome(rs.getString("nome"));
				c.setCpf(rs.getString("cpf"));
				c.setEmail(rs.getString("email"));
				c.setSexo(rs.getString("sexo"));
				c.setTelefone(rs.getString("telefone"));
				c.setEndereco(rs.getString("endereco"));
				c.setNumero(rs.getInt("numero"));
				c.setComplemento(rs.getString("complemento"));
				
				clientes.add(c);
			}
			
			for(Cliente cli : clientes){
				
				String sqlAnimal = "SELECT * FROM animal WHERE 1 = 1 AND id_cliente = ? ";
				
				String queryFinalAnimal = montaWhereDinamicoAnimal(cliente, sqlAnimal);
				
				ps = conexao.prepareStatement(queryFinalAnimal);
				ps.setLong(1, cli.getId());
				rs = ps.executeQuery();
				
				List<Animal> animais = new ArrayList<>();
				while(rs.next()){
					Animal a = new Animal();
					a.setId(rs.getLong("id"));
					a.setNome(rs.getString("nome"));
					a.setSexo(rs.getString("sexo"));
					a.setEspecie(rs.getString("especie"));
					a.setRaca(rs.getString("raca"));
					a.setIdade(rs.getInt("idade"));
					a.setPeso(rs.getInt("peso"));
					a.setObservacoes(rs.getString("observacoes"));
					
					animais.add(a);
				}
				cli.setAnimais(animais);
			}
			
			return clientes;
			
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

	private String montaWhereDinamicoAnimal(Cliente cliente, String sql) {
		StringBuilder query = new StringBuilder(sql);
		
		if(StringUtils.isNotBlank(cliente.getAnimais().get(0).getNome())){
			query.append("AND nome like '%").append(cliente.getAnimais().get(0).getNome()).append("%'");
		} 
		if(StringUtils.isNotBlank(cliente.getAnimais().get(0).getSexo())){
			query.append("AND sexo = ").append(cliente.getAnimais().get(0).getSexo());
		} 
		if(StringUtils.isNotBlank(cliente.getAnimais().get(0).getEspecie())){
			query.append("AND especie like '%").append(cliente.getAnimais().get(0).getEspecie()).append("%'");
		} 
		if(StringUtils.isNotBlank(cliente.getAnimais().get(0).getRaca())){
			query.append("AND raca like '%").append(cliente.getAnimais().get(0).getRaca()).append("%'");
		} 
		if(cliente.getAnimais().get(0).getIdade() != null){
			query.append("AND idade like '%").append(cliente.getAnimais().get(0).getIdade()).append("%'");
		} 
		
		return query.toString();
	}

	public String montaWhereDinamicoCliente(Cliente cliente, String sql) {
		
		StringBuilder query = new StringBuilder(sql);
	
		if(StringUtils.isNotBlank(cliente.getNome())){
			query.append("AND c.nome like '%").append(cliente.getNome()).append("%'");
		} 
		if(StringUtils.isNotBlank(cliente.getEmail())){
			query.append("AND c.email like '%").append(cliente.getEmail()).append("%'");
		} 
		if(StringUtils.isNotBlank(cliente.getSexo())){
			query.append("AND c.sexo = ").append(cliente.getSexo());
		} 
		if(StringUtils.isNotBlank(cliente.getCpf())){
			query.append("AND c.cpf like '%").append(cliente.getCpf()).append("%'");
		} 
		
		return query.toString();
	}
}
