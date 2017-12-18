package br.ifrn.poo.JFinancas.controle;

import java.util.ArrayList;

import br.ifrn.poo.JFinancas.exceptions.SenhaIncorretaException;
import br.ifrn.poo.JFinancas.exceptions.TipoNaoEncontradoException;
import br.ifrn.poo.JFinancas.exceptions.UsuarioNaoCadastradoException;
import br.ifrn.poo.JFinancas.modelo.Tipo;
import br.ifrn.poo.JFinancas.modelo.Usuario;

public class UsuarioController {
	private static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	private static Usuario activeUser;
	private static ArrayList<Tipo> tipos = new ArrayList<Tipo>();
	
//	public UsuarioController () {
//		usuario = new Usuario ();
//	}
	
	public static Usuario getActiveUser () {
		return activeUser;
	}
	
	public static void setActiveUser (Usuario usr) {
		tipos.add (new Tipo("Alimentação"));
		tipos.add (new Tipo("Entrentenimento"));
		tipos.add (new Tipo("Transporte"));
		tipos.add (new Tipo("Contas"));
		tipos.add (new Tipo("Domestico"));
		activeUser = usr;
	}
	
	public static void registrarUsuario (String nome, float saldo, String senha) {
		Usuario usr = new Usuario(saldo, nome, senha);
		usuarios.add(usr);
	}
	
	public static Usuario recuperarUsuario (String nome, String senha) 
			throws UsuarioNaoCadastradoException, SenhaIncorretaException
	{
		for (Usuario usr : usuarios) {
			if (usr.getNome().equals(nome)) {
				if (usr.checkSenha(senha)) return usr;
				else throw new SenhaIncorretaException();
			} 
		}
		throw new UsuarioNaoCadastradoException();
	}
	
	public static ArrayList<Usuario> getUsuarios () {
		return usuarios;
	}
	
	public static ArrayList<Tipo> getTipos() {
		return tipos;
	}
	
	public static Tipo procurarTipo(String nome) throws TipoNaoEncontradoException {
		for(Tipo l: tipos) {
			if(l.getNome().equals(nome)) {
				return l;
			}
		}
		throw new TipoNaoEncontradoException();
	}
	
}
