package br.ifrn.poo.JFinancas.modelo;

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
public class Gasto extends Movimentacao{

	public Gasto(Date data, float valor, String nome, Tipo tipo) {
		super(data, valor, nome, tipo);
		// TODO Auto-generated constructor stub
	}
	
	public Gasto(Date data, float valor, String nome, Tipo tipo, int id) {
		super(data, valor, nome, tipo, id);
		// TODO Auto-generated constructor stub
	}
    
}
