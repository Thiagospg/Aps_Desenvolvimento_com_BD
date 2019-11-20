package com.pdv.model;

import java.util.Date;

public class Venda {

	private Cliente cliente;
	private Produto produto;
	private Localidade localidade;
	private Integer qtd_venda;
	private double valor_total;
	private Date data_venda;

	public Venda() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Venda(Cliente cliente, Produto produto, Localidade localidade, Integer qtd_venda, double valor_total,
			Date data_venda) {
		super();
		this.cliente = cliente;
		this.produto = produto;
		this.localidade = localidade;
		this.qtd_venda = qtd_venda;
		this.valor_total = valor_total;
		this.data_venda = data_venda;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public Integer getQtd_venda() {
		return qtd_venda;
	}

	public void setQtd_venda(Integer qtd_venda) {
		this.qtd_venda = qtd_venda;
	}

	public double getValor_total() {
		return valor_total;
	}

	public void setValor_total(double valor_total) {
		this.valor_total = valor_total;
	}

	public Date getData_venda() {
		return data_venda;
	}

	public void setData_venda(Date data_venda) {
		this.data_venda = data_venda;
	}

}
