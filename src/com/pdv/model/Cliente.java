package com.pdv.model;

public class Cliente {

	private Integer cod_cli;
	private String nome;
	private Integer bonus;
	private Character perfil;
	private Character status;

	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cliente(Integer cod_cli, String nome, Integer bonus, Character perfil, Character status) {
		super();
		this.cod_cli = cod_cli;
		this.nome = nome;
		this.bonus = bonus;
		this.perfil = perfil;
		this.status = status;
	}

	public Integer getCod_cli() {
		return cod_cli;
	}

	public void setCod_cli(Integer cod_cli) {
		this.cod_cli = cod_cli;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getBonus() {
		return bonus;
	}

	public void setBonus(Integer bonus) {
		this.bonus = bonus;
	}

	public Character getPerfil() {
		return perfil;
	}

	public void setPerfil(Character perfil) {
		this.perfil = perfil;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

}
