package br.ifrn.poo.JFinancas.controle;

import java.util.ArrayList;

import br.ifrn.poo.JFinancas.exceptions.UsuarioNaoCadastradoException;
import br.ifrn.poo.JFinancas.modelo.Usuario;

public class UsuarioController {
	private static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	private static Usuario activeUser;
	
//	public UsuarioController () {
//		usuario = new Usuario ();
//	}
	
	public static Usuario getActiveUser () {
		return activeUser;
	}
	
	public static void setActiveUser (Usuario usr) {
		activeUser = usr;
	}
	
	public static void registrarUsuario (String nome, float saldo) {
		Usuario usr = new Usuario(saldo, nome);
		usuarios.add(usr);
	}
	
	public static Usuario recuperarUsuario (String nome) throws UsuarioNaoCadastradoException{
		for (Usuario usr : usuarios) {
			if (usr.getNome().equals(nome)) return usr; 
		}
		throw new UsuarioNaoCadastradoException();
	}
	
	public static ArrayList<Usuario> getUsuarios () {
		return usuarios;
	}
}
