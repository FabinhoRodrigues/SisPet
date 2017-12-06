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

@WebServlet({"/jsp/cadCliente", "/jsp/cadCliente/listaAnimal", "/jsp/listarCliente", "/jsp/excluirCliente"})
public class ClienteServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	private List<Animal> listaAnimal = new ArrayList<Animal>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		execute(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		execute(req, resp);
	}

	private void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
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
		
		Animal animal = new Animal();
		animal.setNome(nomeAnimal);
		animal.setSexo(sexoAnimal);
		animal.setEspecie(especie);
		animal.setRaca(raca);
		animal.setIdade(StringUtils.isNotBlank(idade) ? Integer.parseInt(idade) : null);
		animal.setPeso(StringUtils.isNotBlank(peso) ? Integer.parseInt(peso) : null);
		animal.setObservacoes(observacoes);
		
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
		
		if(url.equalsIgnoreCase("/jsp/cadCliente")){
			cadastrar(req, resp, cliente);
		} else if(url.equalsIgnoreCase("/jsp/cadCliente/listaAnimal")){
			listaAnimal.add(animal);
			
			resp.getWriter().print(animal.getNome());
		} else if(url.equalsIgnoreCase("/jsp/altCliente")){
			//alterar(req, resp, cliente);
			
		} else if(url.equalsIgnoreCase("/jsp/excluirCliente")){
			
			if("C".equals(flagBusca)){
				excluirCliente(req, resp);
			} else if("A".equals(flagBusca)){
				excluirAnimal(req, resp);
	        }
			
		} else if(url.equalsIgnoreCase("/jsp/listarCliente")){
			
			if("C".equals(flagBusca)){
				FiltroDeConsultaCliente filtroCliente = new FiltroDeConsultaCliente();
				filtroCliente.setNome(nomeCliente);
				filtroCliente.setEmail(emailCliente);
				filtroCliente.setSexo(sexoCliente);
				filtroCliente.setCpf(cpf);
	        
				listarCliente(req, resp, filtroCliente, flagBusca);
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
			
			long id_usuario = 0;
			if(usuario != null)
				id_usuario = usuario.getId();
			
			ClienteDAO dao = new ClienteDAO();
			boolean resultadoInsercao = dao.cadastrar(cliente, id_usuario);
			
			String msg = "";
			if(resultadoInsercao){
				msg = "<div class='alert alert-success'>Cadastro efetuado com sucesso!</div>";
			} else {
				msg = "<div class='alert alert-danger'>Erro ao fazer o cadastro</div>";
			}
			
			req.setAttribute("msg", msg);
			req.getRequestDispatcher("cadastroCliente.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	private void listarCliente(HttpServletRequest req, HttpServletResponse resp, FiltroDeConsultaCliente filtroCliente, String flagBusca) {
		try {
			ClienteDAO dao = new ClienteDAO();
			List<Cliente> clientes = dao.listar(filtroCliente);

			if(clientes.isEmpty()){
				String msg = "<div class='alert alert-success'>Nenhum registro foi encontrado.</div>";
				req.setAttribute("msg", msg);
				req.setAttribute("fc", filtroCliente);
			}
			
			req.setAttribute("flagBusca", flagBusca);
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
	
	public void excluirCliente(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try{
			
			Long id = new Long(req.getParameter("id"));
			ClienteDAO dao = new ClienteDAO();
			
			if(dao.excluir(id)){
				req.setAttribute("msg", "<div class='alert alert-success'>Cliente "
						+ " excluído com sucesso!</div>");
			} else {
				req.setAttribute("msg", "<div class='alert alert-danger'>Cliente "
						+ " não excluido</div>");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			req.getRequestDispatcher("registrosDeClientes.jsp").forward(req, resp);
		}
	}
	
	private void excluirAnimal(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try{
			
			Long idAnimal = new Long(req.getParameter("id"));
			ClienteDAO clienteDao = new ClienteDAO();
			AnimalDAO animalDao = new AnimalDAO();

			Animal animal = animalDao.getAnimalById(idAnimal);
			
			int quantidadeDeAnimaisDoCliente = animalDao.getQuantidadeDeAnimaisDoCliente(animal.getId_cliente());
			boolean resultado = false;
			if(quantidadeDeAnimaisDoCliente == 1){
				resultado = clienteDao.excluir(animal.getId_cliente());
			} else if(quantidadeDeAnimaisDoCliente > 1){
				resultado = animalDao.excluir(idAnimal);
			}

			if(resultado){
				req.setAttribute("msg", "<div class='alert alert-success'>Animal "
						+ " excluído com sucesso!</div>");
			} else {
				req.setAttribute("msg", "<div class='alert alert-danger'>Animal "
						+ " não excluido</div>");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			req.getRequestDispatcher("registrosDeClientes.jsp").forward(req, resp);
		}
	}

}
