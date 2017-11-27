package br.com.sispet.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sispet.dao.VeterinarioDAO;
import br.com.sispet.modelo.Usuario;
import br.com.sispet.modelo.Veterinario;

@WebServlet({"/cadVeterinario", "/altVeterinario"})
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
		
		
		String url = req.getServletPath();
		if(url.equalsIgnoreCase("/cadVeterinario")){
			cadastrar(req, resp, veterinario);
		}else if(url.equalsIgnoreCase("/altVeterinario")){
			alterar(req, resp, veterinario);
		}
		
	}

	private void alterar(HttpServletRequest req, HttpServletResponse resp, Veterinario veterinario) {
		try {
			Usuario usuarioLogado = (Usuario) req.getSession().getAttribute("usuarioLogado");
			veterinario.setId(usuarioLogado.getId());
			
			VeterinarioDAO dao = new VeterinarioDAO();
			boolean resultadoAlteracao = dao.alterarVeterinario(veterinario);
			
			String msg = "";
			if(resultadoAlteracao){
				msg = "<div class='alert alert-success'>Edição efetuada com sucesso!</div>";
			} else {
				msg = "<div class='alert alert-danger'>Erro ao fazer a edição</div>";
			}
			
			req.setAttribute("msg", msg);
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	private void cadastrar(HttpServletRequest req, HttpServletResponse resp, Veterinario veterinario) {
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
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

}
