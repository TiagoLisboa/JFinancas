package br.ifrn.poo.JFinancas.modelo;

public class Tipo {
	private String nome;
	private int id = -1;
	
	public Tipo(String nome, int id){
		this.id = id;
		this.nome = nome;
	}
	
	public Tipo(String nome){
		this(nome, -1);
	}
	
	public int getId () {
		return id;
	}
	
	public boolean equals(Object obj) {
		Tipo tipo2 = (Tipo)obj;
		return obj instanceof Tipo && nome.equals(tipo2.getNome());
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return nome;
	}
}
