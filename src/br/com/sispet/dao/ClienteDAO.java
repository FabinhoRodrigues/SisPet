package br.com.sispet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.mysql.jdbc.Statement;

import br.com.sispet.factory.ConnectionFactory;
import br.com.sispet.modelo.Animal;
import br.com.sispet.modelo.Cliente;
import br.com.sispet.modelo.filtro.FiltroDeConsultaCliente;

public class ClienteDAO {

	private Connection conexao;

	public List<Cliente> listar(FiltroDeConsultaCliente filtro) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			conexao = new ConnectionFactory().getConnection();
			
			String sql = "SELECT * FROM cliente WHERE 1 = 1 ";
			
			String queryFinal = montaWhereDinamicoCliente(filtro, sql);
			
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

	public String montaWhereDinamicoCliente(FiltroDeConsultaCliente filtro, String sql) {
		
		StringBuilder query = new StringBuilder(sql);
	
		if(StringUtils.isNotBlank(filtro.getNome())){
			query.append("AND nome like '%").append(filtro.getNome()).append("%'");
		} 
		if(StringUtils.isNotBlank(filtro.getEmail())){
			query.append("AND email like '%").append(filtro.getEmail()).append("%'");
		} 
		if(StringUtils.isNotBlank(filtro.getSexo())){
			query.append("AND sexo = ").append(filtro.getSexo());
		} 
		if(StringUtils.isNotBlank(filtro.getCpf())){
			query.append("AND cpf like '%").append(filtro.getCpf()).append("%'");
		} 
		
		return query.toString();
	}

	public boolean cadastrar(Cliente c, long id_usuario) {
		PreparedStatement ps = null;
		boolean sucesso = false;
		
		try{
			conexao = new ConnectionFactory().getConnection();
			conexao.setAutoCommit(false);
			
			String sql = "insert into cliente (id_veterinario, nome, cpf, email, sexo, telefone, endereco, numero, complemento) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			ps = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, id_usuario);
			ps.setString(2, c.getNome());
			ps.setString(3, c.getCpf());
			ps.setString(4, c.getEmail());
			ps.setString(5, c.getSexo());
			ps.setString(6, c.getTelefone());
			ps.setString(7, c.getEndereco());
			ps.setInt(8, c.getNumero());
			ps.setString(9, c.getComplemento());
			
			ps.execute();
			sucesso = true;
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int id_cliente = rs.getInt(1);
			rs.close();
			
			for(Animal a : c.getAnimais()){
				
				sql = "insert into animal (id_cliente, nome, sexo, especie, raca, idade, peso, observacoes) values (?, ?, ?, ?, ?, ?, ?, ?)";
				
				ps = conexao.prepareStatement(sql);
				ps.setLong(1, id_cliente);
				ps.setString(2, a.getNome());
				ps.setString(3, a.getSexo());
				ps.setString(4, a.getEspecie());
				ps.setString(5, a.getRaca());
				ps.setInt(6, a.getIdade());
				ps.setInt(7, a.getPeso());
				ps.setString(8, a.getObservacoes());
				
				ps.execute();
				sucesso = true;
			}
			
			conexao.commit();
			
		} catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
}
