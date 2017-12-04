package br.com.sispet.modelo.filtro;

public class FiltroDeConsultaAnimal extends FiltroDeConsulta {

	private String especie;

	private String raca;

	private Integer idade;

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

}
