package br.ifrn.poo.JFinancas.modelo;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rute
 */
public class Registradora {
    private ArrayList<Movimentacao> movimentacoes;
    private ArrayList<Limitador> limitadores;
    
    public Registradora() {
    	this.movimentacoes 	= new ArrayList<Movimentacao>();
    	this.limitadores 	= new ArrayList<Limitador>();
	}
    
    public void novaMovimentacao(Movimentacao mov){
        movimentacoes.add(mov);
    }
    
    public void removerMovimentacao(Movimentacao mov){
    	movimentacoes.remove(mov);
    }
    
    public ArrayList<Movimentacao> getMovimentacoes () {
    	return movimentacoes;
    }
    
    public void novoLimitador(Limitador lim){
        limitadores.add(lim);
    }
    
    public void removerLimitador(Limitador lim){
        limitadores.remove(lim);
    }
    
    public ArrayList<Limitador> getLimitadores () {
    	return limitadores;
    }
}
