package br.com.sispet.modelo;

public class FiltroDeConsultaCliente extends FiltroDeConsulta{

	private String email;
	
	private String cpf;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
}
