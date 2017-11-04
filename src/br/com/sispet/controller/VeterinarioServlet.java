package br.com.sispet.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sispet.dao.VeterinarioDAO;
import br.com.sispet.modelo.Veterinario;

@WebServlet({"/cadVeterinario"})
public class VeterinarioServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String nome = req.getParameter("nomeVet");
		String email = req.getParameter("emailVet");
		String especialidade = req.getParameter("espVet");
		String cpf = req.getParameter("cpfVet");
		String tel = req.getParameter("telVet");
		String usuario = req.getParameter("loginVet");
		String senha = req.getParameter("senhaVet");
		
		Veterinario veterinario = new Veterinario();
		veterinario.setNome(nome);
		veterinario.setEmail(email);
		veterinario.setEspecialidade(especialidade);
		veterinario.setTelefone(tel);
		veterinario.setCpf(cpf);
		veterinario.setUsuario(usuario);
		veterinario.setSenha(senha);
		
		cadastrar(veterinario, req, resp);
	}

	private void cadastrar(Veterinario veterinario, HttpServletRequest req, HttpServletResponse resp) {
		try {
			VeterinarioDAO dao = new VeterinarioDAO();
			boolean resultadoInsercao = dao.insereVeterinario(veterinario);
			
			String msg = "";
			if(resultadoInsercao){
				msg = "<div class='alert alert-success'>Cadastro efetuado com sucesso!</div>";
			} else {
				msg = "<div class='alert alert-danger'>Erro ao fazer o cadastro</div>";
			}
			
			req.setAttribute("msg", msg);
			req.getRequestDispatcher("cadastroVeterinario.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

}
