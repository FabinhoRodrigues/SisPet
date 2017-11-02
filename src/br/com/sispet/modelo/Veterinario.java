package br.com.sispet.modelo;

/**
 *
 * @author gabriel.almeida
 */

public class Veterinario {

	private int cpf;

	private String usuario;

	private String senha;

	private String nome;

	private String sobrenome;

	public Veterinario() {

	}

	public Veterinario(int cpf) {
		this.cpf = cpf;
	}

	public Veterinario(String usuario, String senha) {
		this.usuario = usuario;
		this.senha = senha;
	}

	public Veterinario(String nome, String sobrenome, int cpf) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpf = cpf;
	}

	public int getCpf() {
		return cpf;
	}

	public void setCpf(int cpf) {
		this.cpf = cpf;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

}
