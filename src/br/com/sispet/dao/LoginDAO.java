package br.com.sispet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.sispet.factory.ConnectionFactory;
import br.com.sispet.modelo.Administrador;
import br.com.sispet.modelo.Usuario;
import br.com.sispet.modelo.Veterinario;
import br.com.sispet.utils.Utils;

public class LoginDAO {

	Connection con = null;
	
	public Usuario logar(String login, String senha) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			con = new ConnectionFactory().getConnection();
			
			String sql = "SELECT * FROM usuario WHERE usuario = ? and senha = ?";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, login);
			ps.setString(2, Utils.criptografarSenha(senha));
			
			rs = ps.executeQuery();
			rs.next();
			
			Usuario usuario = new Usuario();
			usuario.setId(rs.getLong("id"));
			usuario.setNome(rs.getString("nome"));
			usuario.setPerfil(rs.getString("perfil"));
			usuario.setUsuario(rs.getString("usuario"));
			
			if("VET".equals(usuario.getPerfil())){
				
				sql = "SELECT v.id as id, v.email as email, v.telefone as tel, v.especialidade as esp, v.cpf as cpf "
						+ "FROM usuario u JOIN veterinario v ON u.id = v.id_usuario WHERE id_usuario = ?";
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setLong(1, usuario.getId());
				
				rs = stmt.executeQuery();
				rs.next();
				
				Veterinario vet = new Veterinario();
				vet.setNome(usuario.getNome());
				vet.setUsuario(usuario.getUsuario());
				vet.setPerfil(usuario.getPerfil());
				vet.setId_usuario(usuario.getId());

				vet.setId(rs.getLong("id"));
				vet.setEmail(rs.getString("email"));
				vet.setTelefone(rs.getString("tel"));
				vet.setEspecialidade(rs.getString("esp"));
				vet.setCpf(rs.getString("cpf"));
				
				return vet;
			} else if("ADM".equals(usuario.getPerfil())){
				
				Administrador adm = new Administrador();
				adm.setId(usuario.getId());
				adm.setNome(usuario.getNome());
				adm.setPerfil(usuario.getPerfil());
				adm.setUsuario(login);

				return adm; 
			}
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	

}
