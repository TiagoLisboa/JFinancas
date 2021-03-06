package br.ifrn.poo.JFinancas.modelo;

import java.util.ArrayList;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rute
 */
public class Teto extends Limitador{

	public Teto(String nome, float valor, Date inicio, Date fim, Tipo tipo) {
		super(nome, valor, inicio, fim, tipo);
		// TODO Auto-generated constructor stub
	}
	public Teto(String nome, float valor, Date inicio, Date fim, Tipo tipo, int id) {
		super(nome, valor, inicio, fim, tipo, id);
		// TODO Auto-generated constructor stub
	}
	
	public Teto(int id) {
		super(id);
	}
	
	public float calcularTransacoes (ArrayList<Movimentacao> mov) {
		float total = 0;
		for (Movimentacao m : mov) {
			if (m instanceof Gasto) {
				if ((m.getData().after(this.getInicio()) &&  m.getData().before(this.getFim())) 
						|| m.getData().equals(this.getInicio())  || m.getData().equals(this.getFim())) {
					if (m.getTipo().equals(this.getTipo())) {
						total += m.getValor();
					}
				}
			}
		}
		return total;
	}
	
	public ArrayList<Movimentacao> procurarTransacoes (ArrayList<Movimentacao> mov) {
		ArrayList<Movimentacao> transacoes = new ArrayList<Movimentacao>();
		for (Movimentacao m : mov) {
			if (m instanceof Gasto) {
				if ((m.getData().after(this.getInicio()) &&  m.getData().before(this.getFim())) 
						|| m.getData().equals(this.getInicio())  || m.getData().equals(this.getFim())) {
					if (m.getTipo().equals(this.getTipo())) {
						transacoes.add( m );
					}
				}
			}
		}
		return transacoes;
	}

}
