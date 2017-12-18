package br.ifrn.poo.JFinancas;

import java.sql.*;

public class ConnectionFactory {
	public Connection getConnection() {
        try {
        	String helper = System.getProperty("user.dir");
        	Class.forName("org.sqlite.JDBC").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:sqlite:"+helper+"/dbs/database.db");
            return conn;
        } catch (Exception e) {
        	throw new RuntimeException(e);
		}
    }
}
