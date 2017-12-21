package br.ifrn.poo.JFinancas.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.ifrn.poo.JFinancas.ConnectionFactory;
import br.ifrn.poo.JFinancas.modelo.Meta;
import br.ifrn.poo.JFinancas.modelo.Limitador;
import br.ifrn.poo.JFinancas.modelo.Registradora;
import br.ifrn.poo.JFinancas.modelo.Teto;
import br.ifrn.poo.JFinancas.modelo.Tipo;

public class LimitadorDAO {
	private Connection connection;
	
	public LimitadorDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public ArrayList<Limitador> getByIdRegistradora (Registradora registradora) throws ParseException {
		String sql = "select * from limitadores where id_registradora=(?)";
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		ArrayList<Limitador> limitadores= new ArrayList<Limitador>();
		
		try {
            // prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sql);

            // seta os valores
            stmt.setInt(1, registradora.getId());
            
            // executa
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
            	String nome = rs.getString("nome");
            	int id = rs.getInt("id");
            	float valor = rs.getFloat("valor");
            	Date inicio = df.parse(rs.getString("inicio"));
            	Date fim = df.parse(rs.getString("fim"));
            	String categoria = rs.getString("categoria");
            	
            	TipoDAO tdao = new TipoDAO();
            	Tipo tipo = tdao.getById(new Tipo("", rs.getInt("id_tipo")));
            	tdao.close();
            	
            	if (categoria.equals("Teto")) limitadores.add(new Teto(nome, valor, inicio, fim, tipo, id));
            	else limitadores.add(new Meta(nome, valor, inicio, fim, tipo, id));
            }
            
            rs.close();
            stmt.close();
            
            return limitadores;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}
	
	public void adiciona(Registradora r, Limitador limitador) {
		String sql1 = "insert into limitadores (id_registradora, id_tipo, nome, valor, inicio, fim, categoria)"
				+ "values (?, ?, ?, ?, ?, ?, ?)";
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
        try {
            // prepared statement para inserção
        	PreparedStatement stmt = connection.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
        	
        	int id_registradora = r.getId();
        	int id_tipo= limitador.getTipo().getId();
        	String nome = limitador.getNome();
        	float valor = limitador.getValor();
        	Date inicio = limitador.getInicio();
        	Date fim = limitador.getFim();
        	String categoria = limitador instanceof Teto ? "Teto" : "Meta";
        	
        	stmt.setInt(1, id_registradora);
        	stmt.setInt(2, id_tipo);
        	stmt.setString(3, nome);
        	stmt.setFloat(4, valor);
        	stmt.setString(5, df.format(inicio));
        	stmt.setString(6, df.format(fim));
        	stmt.setString(7, categoria);
        	
        	// insere
        	int affectedRows = stmt.executeUpdate();
        	
        	if (affectedRows == 0)
        		throw new SQLException("Não foi possivel cadastrar limitador");
        	
                	
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
	
	public void editar(Registradora r, Limitador limitador) {
		String sql1 = "UPDATE limitadores " + 
				"SET id_registradora=?, id_tipo=?, nome=?, valor=?, inicio=?, fim=?, categoria=? " + 
				"WHERE id=?";
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
        try {
            // prepared statement para inserção
        	PreparedStatement stmt = connection.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
        	
        	
        	int id_registradora = r.getId();
        	int id_tipo= limitador.getTipo().getId();
        	String nome = limitador.getNome();
        	float valor = limitador.getValor();
        	Date inicio = limitador.getInicio();
        	Date fim = limitador.getFim();
        	String categoria = limitador instanceof Teto ? "Teto" : "Meta";

       
        	
        	stmt.setInt(1, id_registradora);
        	stmt.setInt(2, id_tipo);
        	stmt.setString(3, nome);
        	stmt.setFloat(4, valor);
        	stmt.setString(5, df.format(inicio));
        	stmt.setString(6, df.format(fim));
        	stmt.setString(7, categoria);
        	stmt.setInt(8, limitador.getId());
        	
        	// insere
        	int affectedRows = stmt.executeUpdate();
        	
        	if (affectedRows == 0)
        		throw new SQLException("Não foi possivel cadastrar limitador");
        	
                	
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
	
	public void deletar(Limitador limitador) {
		String sql1 = "DELETE FROM limitadores " + 
				"WHERE id=?";
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
        try {
            // prepared statement para inserção
        	PreparedStatement stmt = connection.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
        	
        	stmt.setInt(1, limitador.getId());
        	
        	// insere
        	int affectedRows = stmt.executeUpdate();
        	
        	if (affectedRows == 0)
        		throw new SQLException("Não foi possivel deletar limitador");
        	
                	
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
