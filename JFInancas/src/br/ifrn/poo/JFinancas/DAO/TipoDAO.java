package br.ifrn.poo.JFinancas.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.ifrn.poo.JFinancas.ConnectionFactory;
import br.ifrn.poo.JFinancas.modelo.Tipo;

public class TipoDAO {
	private Connection connection;
	
	public TipoDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public ArrayList<Tipo> getAll () {
		ArrayList<Tipo> tipos = new ArrayList<Tipo>();
	
		String sql = "select * from tipos";
        
		try {
			// prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sql);

            // executa
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
            	String nome = rs.getString("nome");
            	int id = rs.getInt("id");
            	tipos.add(new Tipo(nome, id));
            }
            
            rs.close();
            stmt.close();
            
            return tipos;
        } catch (SQLException e) {
        	throw new RuntimeException(e);
        }
    }
	
	public Tipo getById (Tipo tipo) {
		String sql = "select * from tipos where id=(?)";

        try {
            // prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sql);

            // seta os valores

            stmt.setInt(1,tipo.getId());
            // executa
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
            	String nome = rs.getString("nome");
            	int id = rs.getInt("id");
            	tipo = new Tipo(nome, id);
            }
            
            rs.close();
            stmt.close();
            
            return tipo;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}
	
	public void close () {
		try {
			this.connection.close();
			this.connection = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
