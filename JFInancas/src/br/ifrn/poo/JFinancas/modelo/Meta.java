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
public class Meta extends Limitador {

	public Meta(String nome, float valor, Date inicio, Date fim, String tipo) {
		super(nome, valor, inicio, fim, tipo);
		// TODO Auto-generated constructor stub
	}
	

	public float calcularTransacoes (ArrayList<Movimentacao> mov) {
		float total = 0;
		for (Movimentacao m : mov) {
			if (m instanceof Ganho) {
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
    
}
