package br.com.sispet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sispet.modelo.Usuario;

@WebFilter(filterName = "UsuarioLogado", urlPatterns = {"/jsp/cadastroCliente.jsp", "/jsp/registrosDeClientes.jsp"})
public class UsuarioLogado implements Filter{
	
	private String contextPath;
	   
    public UsuarioLogado() {
    }

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
	   
		HttpServletResponse res = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
//		Usuario u = (Usuario) session.getAttribute("usuarioLogado");
//		if (u == null) {
//			session.invalidate();
//			res.sendRedirect(contextPath + "/jsp/index.jsp");
//		} else {
			res.setHeader("Cache-control", "no-cache, no-store");
			res.setHeader("Pragma", "no-cache");
			res.setHeader("Expires", "-1");
			chain.doFilter(request, response);
//		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.contextPath = filterConfig.getServletContext().getContextPath();		
	}

	
}
