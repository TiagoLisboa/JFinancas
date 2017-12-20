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
import br.ifrn.poo.JFinancas.modelo.Ganho;
import br.ifrn.poo.JFinancas.modelo.Gasto;
import br.ifrn.poo.JFinancas.modelo.Movimentacao;
import br.ifrn.poo.JFinancas.modelo.Registradora;
import br.ifrn.poo.JFinancas.modelo.Tipo;

public class MovimentacaoDAO {
	private Connection connection;
	
	public MovimentacaoDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public ArrayList<Movimentacao> getByIdRegistradora (Registradora registradora) throws ParseException {
		String sql = "select * from movimentacoes where id_registradora=?";
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		ArrayList<Movimentacao> movimentacoes = new ArrayList<Movimentacao>();
		
		try {
            // prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sql);

            // seta os valores
            stmt.setInt(1, registradora.getId());
            
            // executa
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
            	String nome = rs.getString("nome");
            	System.out.println(nome);
            	int id = rs.getInt("id");
            	float valor = rs.getFloat("valor");
            	Date data = df.parse(rs.getString("data"));
            	String categoria = rs.getString("categoria");
            	
            	TipoDAO tdao = new TipoDAO();
            	Tipo tipo = tdao.getById(new Tipo("", rs.getInt("id_tipo")));
            	tdao.close();
            	
            	if (categoria.equals("Gasto")) movimentacoes.add(new Gasto(data, valor, nome, tipo, id));
            	else movimentacoes.add(new Ganho(data, valor, nome, tipo, id));
            }
            
            rs.close();
            stmt.close();
            
            return movimentacoes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}
	
	public void adiciona(Registradora r, Movimentacao movimentacao) {
		String sql1 = "insert into movimentacoes (id_registradora, id_tipo, nome, valor, data, categoria)"
				+ "values (?, ?, ?, ?, ?, ?)";
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
        try {
            // prepared statement para inserção
        	PreparedStatement stmt = connection.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
        	
        	int id_registradora = r.getId();
        	int id_tipo= movimentacao.getTipo().getId();
        	String nome = movimentacao.getNome();
        	float valor = movimentacao.getValor();
        	Date data = movimentacao.getData();
        	String categoria = movimentacao instanceof Gasto ? "Gasto" : "Ganho";
        	
        	stmt.setInt(1, id_registradora);
        	stmt.setInt(2, id_tipo);
        	stmt.setString(3, nome);
        	stmt.setFloat(4, valor);
        	stmt.setString(5, df.format(data));
        	stmt.setString(6, categoria);
        	
        	// insere
        	int affectedRows = stmt.executeUpdate();
        	
        	if (affectedRows == 0)
        		throw new SQLException("Não foi possivel cadastrar movimentação");
        	
                	
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
