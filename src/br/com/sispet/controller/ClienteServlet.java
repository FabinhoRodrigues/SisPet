package br.com.sispet.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import br.com.sispet.dao.ClienteDAO;
import br.com.sispet.modelo.Animal;
import br.com.sispet.modelo.Cliente;

@WebServlet({"/listarCliente"})
public class ClienteServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

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
		String emailCliente = req.getParameter("nomeEmail");
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
		
		List<Animal> animais = new ArrayList<Animal>();
		animais.add(animal);
		
		Cliente cliente = new Cliente();
		cliente.setNome(nomeCliente);
		cliente.setEmail(emailCliente);
		cliente.setSexo(sexoCliente);
		cliente.setCpf(cpf);
		cliente.setTelefone(tel);
		cliente.setEndereco(endereco);
		cliente.setNumero(StringUtils.isNotBlank(numeroEndereco) ? Integer.parseInt(numeroEndereco) : null);
		cliente.setComplemento(complementoEndereco);
		cliente.setAnimais(animais);
		
		String url = req.getServletPath();
		if(url.equalsIgnoreCase("/cadVeterinario")){
			//cadastrar(req, resp, cliente);
		} else if(url.equalsIgnoreCase("/altVeterinario")){
			//alterar(req, resp, cliente);
		} else if(url.equalsIgnoreCase("/listarCliente")){
			listar(req, resp, cliente, flagBusca);
		} 
	}

	private void listar(HttpServletRequest req, HttpServletResponse resp, Cliente cliente, String flagBusca) {
		try {
			ClienteDAO dao = new ClienteDAO();
			List<Cliente> clientes = dao.listar(cliente);

			if(clientes == null){
//				msg = "<div class='alert alert-success'>Edição efetuada com sucesso!</div>";
//				Mensagem de que não foi encontrado nenhum cliente.
			}
			
	        if("C".equals(flagBusca)){
	        	req.setAttribute("clientes", clientes);
	        } else if("A".equals(flagBusca)){
	        	List<Animal> animais = new ArrayList<>();
	        	for(Cliente c : clientes){
	        		for(Animal a : c.getAnimais()){
	        			animais.add(a);
	        		}
	        	}
	        	req.setAttribute("animais", animais);
	        }
	        
//			req.setAttribute("msg", msg);
			req.setAttribute("flag", flagBusca);
			req.getRequestDispatcher("registrosDeClientes.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
}
