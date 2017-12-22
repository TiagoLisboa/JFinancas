package br.ifrn.poo.JFinancas.controle;

import java.util.ArrayList;

import br.ifrn.poo.JFinancas.modelo.Tipo;
import br.ifrn.poo.JFinancas.DAO.TipoDAO;
import br.ifrn.poo.JFinancas.modelo.Usuario;

public class UsuarioController {
	private static Usuario activeUser;
	private static ArrayList<Tipo> tipos = new ArrayList<Tipo>();
	
	public static Usuario getActiveUser () {
		return activeUser;
	}
	
	public static void setActiveUser (Usuario usr) {
		TipoDAO tdao = new TipoDAO();
		tipos = tdao.getAll();
		tdao.close();
		activeUser = usr;
	}
	
	public static ArrayList<Tipo> getTipos() {
		return tipos;
	}
	
	public static void setTipos(ArrayList<Tipo> s) {
		tipos = s;
	}
	
}
