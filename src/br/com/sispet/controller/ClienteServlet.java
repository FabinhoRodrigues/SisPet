package br.com.sispet.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import br.com.sispet.dao.AnimalDAO;
import br.com.sispet.dao.ClienteDAO;
import br.com.sispet.modelo.Animal;
import br.com.sispet.modelo.Cliente;
import br.com.sispet.modelo.Usuario;
import br.com.sispet.modelo.filtro.FiltroDeConsultaAnimal;
import br.com.sispet.modelo.filtro.FiltroDeConsultaCliente;

@WebServlet({"/sistema/cadCliente", "/sistema/cadCliente/listaAnimal", "/sistema/listarCliente"})
public class ClienteServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	private List<Animal> listaAnimal = new ArrayList<Animal>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String flagBusca = req.getParameter("flagBusca");
		
		/* Cliente */	
		String nomeCliente = req.getParameter("nomeCliente");
		String emailCliente = req.getParameter("emailCliente");
		String sexoCliente = req.getParameter("sexoCliente");
		String cpf = req.getParameter("cpfCliente");
		String tel = req.getParameter("telCliente");
		String endereco = req.getParameter("enderecoCliente");
		String numeroEndereco = req.getParameter("numeroEndCliente");
		String complementoEndereco = req.getParameter("complementoEndCliente");
		
		/* Animal */
		String nomeAnimal = req.getParameter("nomeAnimal");
		String sexoAnimal = req.getParameter("sexoAnimal");
		String especie = req.getParameter("especieAnimal");
		String raca = req.getParameter("racaAnimal");
		String idade = req.getParameter("idadeAnimal");
		String peso = req.getParameter("pesoAnimal");
		String observacoes = req.getParameter("observacoesAnimal");
		String fotoAnimal = req.getParameter("fotoAnimal");
		

		
		Animal animal = new Animal();
		
		animal.setNome(nomeAnimal);
		animal.setSexo(sexoAnimal);
		animal.setEspecie(especie);
		animal.setRaca(raca);
		animal.setIdade(StringUtils.isNotBlank(idade) ? Integer.parseInt(idade) : null);
		animal.setPeso(StringUtils.isNotBlank(peso) ? Integer.parseInt(peso) : null);
		animal.setObservacoes(observacoes);
		animal.setFoto(fotoAnimal);
		
		Cliente cliente = new Cliente();
		cliente.setNome(nomeCliente);
		cliente.setEmail(emailCliente);
		cliente.setSexo(sexoCliente);
		cliente.setCpf(cpf);
		cliente.setTelefone(tel);
		cliente.setEndereco(endereco);
		cliente.setNumero(StringUtils.isNotBlank(numeroEndereco) ? Integer.parseInt(numeroEndereco) : null);
		cliente.setComplemento(complementoEndereco);
		cliente.setAnimais(listaAnimal);
		
		String url = req.getServletPath();
		
		if(url.equalsIgnoreCase("/sistema/cadCliente")){
			cadastrar(req, resp, cliente);
		} else if(url.equalsIgnoreCase("/sistema/cadCliente/listaAnimal")){
			listaAnimal.add(animal);
			
			resp.getWriter().print(animal.getNome());
		} else if(url.equalsIgnoreCase("/sistema/altCliente")){
			//alterar(req, resp, cliente);
		} else if(url.equalsIgnoreCase("/sistema/listarCliente")){
			
			if("C".equals(flagBusca)){
				FiltroDeConsultaCliente filtroCliente = new FiltroDeConsultaCliente();
				filtroCliente.setNome(nomeCliente);
				filtroCliente.setEmail(emailCliente);
				filtroCliente.setSexo(sexoCliente);
				filtroCliente.setCpf(cpf);
	        
				listarCliente(req, resp, filtroCliente);
			} else if("A".equals(flagBusca)){
				FiltroDeConsultaAnimal filtroAnimal = new FiltroDeConsultaAnimal();
				filtroAnimal.setNome(nomeAnimal);
				filtroAnimal.setSexo(sexoCliente);
				filtroAnimal.setEspecie(especie);
				filtroAnimal.setRaca(raca);
				filtroAnimal.setIdade(StringUtils.isNotBlank(idade) ? Integer.parseInt(idade) : null);
				
				listarAnimal(req, resp, filtroAnimal, flagBusca);
	        }
			
			
		} 
	}

	private void cadastrar(HttpServletRequest req, HttpServletResponse resp, Cliente cliente) {
		try {
			HttpSession session = req.getSession();
			Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
			long id_usuario = usuario.getId();
			
			ClienteDAO dao = new ClienteDAO();
			boolean resultadoInsercao = dao.cadastrar(cliente, id_usuario);
			
			String msg = "";
			if(resultadoInsercao){
				msg = "<div class='alert alert-success'>Cadastro efetuado com sucesso!</div>";
			} else {
				msg = "<div class='alert alert-danger'>Erro ao fazer o cadastro</div>";
			}
			
			req.setAttribute("msg", msg);
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	private void listarCliente(HttpServletRequest req, HttpServletResponse resp, FiltroDeConsultaCliente filtroCliente) {
		try {
			ClienteDAO dao = new ClienteDAO();
			List<Cliente> clientes = dao.listar(filtroCliente);

			if(clientes.isEmpty()){
				String msg = "<div class='alert alert-success'>Nenhum registro foi encontrado.</div>";
				req.setAttribute("msg", msg);
				req.setAttribute("fc", filtroCliente);
			}
			
	        req.setAttribute("clientes", clientes);
			req.getRequestDispatcher("registrosDeClientes.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private void listarAnimal(HttpServletRequest req, HttpServletResponse resp, FiltroDeConsultaAnimal filtroAnimal, String flagBusca) {
		try {
			AnimalDAO dao = new AnimalDAO();
			List<Animal> animais = dao.listar(filtroAnimal);

			if(animais.isEmpty()){
				String msg = "<div class='alert alert-success'>Nenhum registro foi encontrado.</div>";
				req.setAttribute("msg", msg);
				req.setAttribute("fa", filtroAnimal);
			}
			
			req.setAttribute("flagBusca", flagBusca);
        	req.setAttribute("animais", animais);
			req.getRequestDispatcher("registrosDeClientes.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

}
