package br.ifrn.poo.JFinancas;


import java.util.Date;

/**
 *
 * @author rute
 */
public abstract class Movimentacao {
    private Date data;
    private float valor;
    private String nome;
    private String tipo;
    
	public Movimentacao(Date data, float valor, String nome, String tipo) {
		super();
		this.data = data;
		this.valor = valor;
		this.nome = nome;
		this.tipo = tipo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
    
	
    
}
