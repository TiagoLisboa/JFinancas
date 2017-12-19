package br.ifrn.poo.JFinancas.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

import br.ifrn.poo.JFinancas.ConnectionFactory;
import br.ifrn.poo.JFinancas.modelo.Registradora;

public class RegistradoraDAO {
	private Connection connection;
	
	public RegistradoraDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public Registradora getById (Registradora registradora) throws ParseException {
		LimitadorDAO ldao = new LimitadorDAO();
		registradora.setLimitadores(ldao.getByIdRegistradora(registradora));
		ldao.close();
		
		MovimentacaoDAO mdao = new MovimentacaoDAO();
		registradora.setMovimentacoes(mdao.getByIdRegistradora(registradora));
		mdao.close();
		
		return registradora;
		
	}
	
	
	public void close () {
		try {
			this.connection.close();
			this.connection = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
