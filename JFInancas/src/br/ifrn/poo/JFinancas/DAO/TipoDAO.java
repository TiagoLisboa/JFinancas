package br.ifrn.poo.JFinancas.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.ifrn.poo.JFinancas.ConnectionFactory;
import br.ifrn.poo.JFinancas.modelo.Tipo;

public class TipoDAO {
	private Connection connection;
	
	public TipoDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void adiciona(Tipo tipo) throws SQLException{
        String sql = "insert into tipos" +
                "(nome)" +
                " values (?)";
        
        int id = 0;
        
        
        // prepared statement para inserção
    	PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    	
    	stmt.setString(1, tipo.getNome());
    	
    	int affectedRows = stmt.executeUpdate();
    	
    	if (affectedRows == 0)
    		throw new SQLException("Não foi possivel cadastrar tipo");
    	
    	
        stmt.close();
    
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
	
	public Tipo getById (Tipo tipo) throws SQLException {
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
	
	public Tipo getByName (Tipo tipo) throws SQLException {
		String sql = "select * from tipos where nome=(?)";

    
        // prepared statement para inserção
        PreparedStatement stmt = connection.prepareStatement(sql);

        // seta os valores

        stmt.setString(1,tipo.getNome());
        // executa
        ResultSet rs = stmt.executeQuery();
        
        tipo = null;
        
        if (rs.next()) {
        	String nome = rs.getString("nome");
        	int id = rs.getInt("id");
        	tipo = new Tipo(nome, id);
        } else {
        	return null;
        }
        
        rs.close();
        stmt.close();
        
        return tipo;
        
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
