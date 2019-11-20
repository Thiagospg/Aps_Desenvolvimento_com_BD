package com.pdv.model;

public class Localidade {

	private Integer cod_local;
	private String nome;
	private String endereco;
	private String telefone;

	public Localidade() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Localidade(Integer cod_local, String nome, String endereco, String telefone) {
		super();
		this.cod_local = cod_local;
		this.nome = nome;
		this.endereco = endereco;
		this.telefone = telefone;
	}

	public Integer getCod_local() {
		return cod_local;
	}

	public void setCod_local(Integer cod_local) {
		this.cod_local = cod_local;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
