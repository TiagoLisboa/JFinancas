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
	private int id;
    private float saldo;
    private String nome;
    private String senha;
    private Registradora registradora;
    
    public Usuario(float saldo, String nome, String senha, int id) {
		super();
		this.id = id;
		this.saldo = saldo;
		this.nome = nome;
		this.senha = senha;
		this.registradora = new Registradora();
	}
    
    public Usuario(float saldo, String nome, String senha) {
    	this(saldo, nome, senha, -1);
    }
    
    public int getId () {
    	return id;
    }
    
    public boolean checkSenha (String senha) {
    	return this.senha.equals(senha);
    }
    
    public String getSenha () {
    	return senha;
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
