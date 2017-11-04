package br.com.sispet.controller;

import java.io.IOException;
import java.sql.SQLException;

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
		
		String cpf = req.getParameter("cpfVet");
		String usuario = req.getParameter("loginVet");
		String senha = req.getParameter("senhaVet");
		String nome = req.getParameter("nomeVet");
		String sobrenome = req.getParameter("sobrenomeVet");
		
		Veterinario veterinario = new Veterinario();
		veterinario.setCpf(cpf);
		veterinario.setUsuario(usuario);
		veterinario.setSenha(senha);
		veterinario.setNome(nome);
		veterinario.setSobrenome(sobrenome);
		
		cadastrar(veterinario, req, resp);
	}

	private void cadastrar(Veterinario veterinario, HttpServletRequest req, HttpServletResponse resp) {
		VeterinarioDAO dao = new VeterinarioDAO();
		boolean resultadoInsercao = dao.insereVeterinario(veterinario);
	}

}
