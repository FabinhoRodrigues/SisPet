package br.com.sispet.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sispet.dao.LoginDAO;
import br.com.sispet.modelo.Usuario;

@WebServlet("/jsp/login")
public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		
		HttpSession session = req.getSession();
		
		Usuario usuario = new LoginDAO().logar(login, senha);
		if(usuario != null){
			req.setAttribute("usuario", usuario);
			session.setAttribute("usuarioLogado", usuario);	
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		} else {
			req.setAttribute("msg", "<div class='alert alert-danger'>Erro ao fazer o login</div>");
			session.setAttribute("usuarioLogado", usuario);
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
		
	}
	
}
