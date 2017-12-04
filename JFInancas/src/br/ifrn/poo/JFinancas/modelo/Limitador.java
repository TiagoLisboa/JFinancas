package br.ifrn.poo.JFinancas.modelo;


import java.util.Date;

/**
 *
 * @author rute
 */
public abstract class Limitador {
    private float valor;
    private Date inicio;
    private Date fim;
    private String tipo;
    
	public Limitador(float valor, Date inicio, Date fim, String tipo) {
		super();
		this.valor = valor;
		this.inicio = inicio;
		this.fim = fim;
		this.tipo = tipo;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
    
    
}
