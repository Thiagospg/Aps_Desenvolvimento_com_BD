package com.pdv.model;

public class Produto {

	private Integer cod_prod;
	private Localidade localidade;
	private String descricao;
	private Integer qtd_estoque;
	private double preco_unitario;
	private String local_fab;

	public Produto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Produto(Integer cod_prod, Localidade localidade, String descricao, Integer qtd_estoque,
			double preco_unitario, String local_fab) {
		super();
		this.cod_prod = cod_prod;
		this.localidade = localidade;
		this.descricao = descricao;
		this.qtd_estoque = qtd_estoque;
		this.preco_unitario = preco_unitario;
		this.local_fab = local_fab;
	}

	public Integer getCod_prod() {
		return cod_prod;
	}

	public void setCod_prod(Integer cod_prod) {
		this.cod_prod = cod_prod;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQtd_estoque() {
		return qtd_estoque;
	}

	public void setQtd_estoque(Integer qtd_estoque) {
		this.qtd_estoque = qtd_estoque;
	}

	public double getPreco_unitario() {
		return preco_unitario;
	}

	public void setPreco_unitario(double preco_unitario) {
		this.preco_unitario = preco_unitario;
	}

	public String getLocal_fab() {
		return local_fab;
	}

	public void setLocal_fab(String local_fab) {
		this.local_fab = local_fab;
	}

}
