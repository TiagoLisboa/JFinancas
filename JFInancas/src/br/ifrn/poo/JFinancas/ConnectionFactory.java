package br.ifrn.poo.JFinancas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class ConnectionFactory {
	public Connection getConnection() {
        try {
        	String path = System.getProperty("user.home")+"/dbs/jfinancas.db";
        	
        	File theFile = new File(path);
        	
        	if (!theFile.exists()) {
        		theFile.getParentFile().mkdirs();
        		
        		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            	URL input = classLoader.getResource("bd/database.db.sql");
            	
            	String command = "sqlite3 " + path;
            	
            	FileReader fr = new FileReader (input.getPath());
            	BufferedReader br = new BufferedReader(fr);
            	
            	Process p = Runtime.getRuntime().exec(command);
        		OutputStream os = p.getOutputStream();
        		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
            	
            	String line;
            	while ((line = br.readLine()) != null) {
            		writer.write(line+"\n");
            	}
            	writer.flush();
            	
            	ConnectionFactory cf = new ConnectionFactory ();
            	
            	return cf.getConnection();
        	}
        	
        	
        	
        	Context ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/database");
            
            Connection conn = ds.getConnection();
        	
  
            return conn;
        } catch (Exception e) {
        	throw new RuntimeException(e);
		}
    }
}
