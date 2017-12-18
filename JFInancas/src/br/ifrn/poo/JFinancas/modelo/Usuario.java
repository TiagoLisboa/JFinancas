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
public class Usuario {
    private float saldo;
    private String nome;
    private String senha;
    private Registradora registradora;
    
    public Usuario(float saldo, String nome, String senha) {
		super();
		this.saldo = saldo;
		this.nome = nome;
		this.senha = senha;
		this.registradora = new Registradora();
	}
    
    public boolean checkSenha (String senha) {
    	return this.senha.equals(senha);
    }
    
    public void setSenha (String senha) {
    	this.senha = senha;
    }

	public float getSaldo() {
		return saldo;
	}



	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}


	public Registradora getRegistradora(){
		return this.registradora;
	}
}
