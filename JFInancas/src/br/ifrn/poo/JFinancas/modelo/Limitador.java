package br.ifrn.poo.JFinancas.modelo;


import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author rute
 */
public abstract class Limitador {
    private float valor;
    private Date inicio;
    private Date fim;
    private String nome;
    private Tipo tipo;
    private int id;
    
	public Limitador(String nome, float valor, Date inicio, Date fim, Tipo tipo, int id) {
		super();
		this.id = id;
		this.valor = valor;
		this.nome = nome;
		this.inicio = inicio;
		this.fim = fim;
		this.tipo = tipo;
	}
	
	public Limitador(int id) {
		super();
		this.id = id;
	}
	public Limitador(String nome, float valor, Date inicio, Date fim, Tipo tipo) {
		this(nome, valor, inicio, fim, tipo, -1);
	}
	
	public int getId () {
		return id;
	}
	
	public float calcularTransacoes (ArrayList<Movimentacao> mov) {
		return 0;
	}
	
	public ArrayList<Movimentacao> procurarTransacoes (ArrayList<Movimentacao> mov) {
		return new ArrayList<Movimentacao>();
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

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
    
}
