package br.com.sispet.modelo;

/**
 *
 * @author gabriel.almeida
 */

public class Veterinario {
	
	private long id;

	private String cpf;

	private String usuario;

	private String senha;

	private String nome;

	private String sobrenome;
	
	private String telefone;

	public Veterinario() {

	}

	public Veterinario(String cpf) {
		this.cpf = cpf;
	}

	public Veterinario(String usuario, String senha) {
		this.usuario = usuario;
		this.senha = senha;
	}

	public Veterinario(String nome, String sobrenome, String cpf, String telefone) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpf = cpf;
		this.telefone = telefone;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
}
