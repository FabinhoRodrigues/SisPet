package br.com.sispet.controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sispet.modelo.Email;

@WebServlet("/enviarEmail")
public class EmailServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			Email email = new Email();
			
			String mailServer = "smtp.gmail.com";   
			String assunto = "Contato SisPet";   
			String para =  "vfg2012@gmail.com";   
			String de = req.getParameter("emailRemetente");   
			String mensagem;  
			mensagem = "<b>Nome: </b>" + req.getParameter("nomeRemetente") + "<br><br>" 
    	 		 	 + "<b>E-mail: </b>" + req.getParameter("emailRemetente") + "<br><br>" 
					 + "<b>Mensagem: </b>" + req.getParameter("msgRemetente")   
					 + " <br><br>";  
			
			String respostaEmail = email.sendMail(mailServer, assunto, para, de, mensagem);
			req.setAttribute("msg", respostaEmail);
			req.getRequestDispatcher("ok.jsp").forward(req, resp);
		} catch (MessagingException e) {
			e.printStackTrace();
		}  
	}
}
