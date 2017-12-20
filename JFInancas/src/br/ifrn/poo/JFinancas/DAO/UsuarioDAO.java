package br.ifrn.poo.JFinancas.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.text.ParseException;

import br.ifrn.poo.JFinancas.ConnectionFactory;
import br.ifrn.poo.JFinancas.exceptions.LoginOuSenhaIncorretoException;
import br.ifrn.poo.JFinancas.exceptions.UsuarioJaCadastradoExcpetion;
import br.ifrn.poo.JFinancas.modelo.Registradora;
import br.ifrn.poo.JFinancas.modelo.Usuario;

public class UsuarioDAO {
	private Connection connection;
	
	public UsuarioDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void adiciona(Usuario usuario) throws UsuarioJaCadastradoExcpetion {
		String sql1 = "insert into registradoras default values";
		
        String sql = "insert into usuarios " +
                "(nome,saldo,senha,id_registradora)" +
                " values (?,?,?,?)";
        int id = 0;
        
        try {
            // prepared statement para inserção
        	PreparedStatement stmt = connection.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
        	
        	int affectedRows = stmt.executeUpdate();
        	
        	if (affectedRows == 0)
        		throw new SQLException("Não foi possivel cadastrar usuario");

        	
        	try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = (int)generatedKeys.getLong(1);
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        	
            stmt.close();
        	
            stmt = connection.prepareStatement(sql);

            // seta os valores

            stmt.setString(1,usuario.getNome());
            stmt.setFloat(2,usuario.getSaldo());
            stmt.setString(3,usuario.getSenha());
            stmt.setInt(4, id);

            // executa
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
        	throw new UsuarioJaCadastradoExcpetion();
        }
    }
	
	public Usuario login(Usuario usuario) throws LoginOuSenhaIncorretoException, ParseException {
        String sql = "select * from usuarios where nome=(?) and senha=(?)";

        try {
            // prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sql);

            // seta os valores

            stmt.setString(1,usuario.getNome());
            stmt.setString(2,usuario.getSenha());
            // executa
            ResultSet rs = stmt.executeQuery();
            
            Usuario usr = null;
            
            if (rs.next()) {
            	String nome = rs.getString("nome");
            	String senha = rs.getString("senha");
            	float saldo = rs.getFloat("saldo");
            	int id = rs.getInt("id");
            	RegistradoraDAO rdao = new RegistradoraDAO();
            	Registradora registradora = rdao.getById(new Registradora(rs.getInt("id_registradora")));
            	usr = new Usuario(registradora, saldo, nome, senha, id);
            }
            
            rs.close();
            stmt.close();
            
            System.out.println(usr);
            
            if (usr != null)
            	return usr;
            else
            	throw new LoginOuSenhaIncorretoException();
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
