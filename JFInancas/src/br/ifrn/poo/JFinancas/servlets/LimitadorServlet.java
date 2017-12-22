package br.ifrn.poo.JFinancas.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ifrn.poo.JFinancas.DAO.LimitadorDAO;
import br.ifrn.poo.JFinancas.DAO.TipoDAO;
import br.ifrn.poo.JFinancas.DAO.UsuarioDAO;
import br.ifrn.poo.JFinancas.controle.UsuarioController;
import br.ifrn.poo.JFinancas.modelo.Meta;
import br.ifrn.poo.JFinancas.modelo.Registradora;
import br.ifrn.poo.JFinancas.modelo.Teto;
import br.ifrn.poo.JFinancas.modelo.Tipo;

/**
 * Servlet implementation class LimitadorServlet
 */
@WebServlet("/novoLimitador")
public class LimitadorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LimitadorServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (UsuarioController.getActiveUser() == null) 
			response.sendRedirect("login");
		else
			response.sendRedirect("novoLimitador.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		TipoDAO tdao = new TipoDAO();
		UsuarioDAO udao = new UsuarioDAO();
		
		String data1 = request.getParameter("data1");
		String data2 = request.getParameter("data2");
		String nome = request.getParameter("nome");
		float valor = Float.parseFloat(request.getParameter("valor"));
		
		Tipo ntipo = new Tipo(request.getParameter("tipo"), -1);
		Tipo tipo = null;
		try {
			tipo = tdao.getByName(ntipo);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (tipo == null) {
			try {
				tdao.adiciona(ntipo);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		try {
			tipo = tdao.getByName(ntipo);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String categoria = request.getParameter("categoria");
		
		LimitadorDAO ldao = new LimitadorDAO();
		
		Registradora r = UsuarioController.getActiveUser().getRegistradora();
		
		try {
			Date dataInicial = sdf.parse(data1);
			Date dataFinal = sdf.parse(data2);
			
			if(categoria.equals("Teto")){
				UsuarioController.getActiveUser().getRegistradora().novoLimitador(new Teto(nome, valor, dataInicial, dataFinal, tipo));
			} else {
				UsuarioController.getActiveUser().getRegistradora().novoLimitador(new Meta(nome, valor, dataInicial, dataFinal, tipo));
			}
			
			if(categoria.equals("Teto")){
				ldao.adiciona(r, new Teto(nome, valor, dataInicial, dataFinal, tipo));
			} else {
				ldao.adiciona(r, new Meta(nome, valor, dataInicial, dataFinal, tipo));
			}
			
			
			UsuarioController.setActiveUser(udao.login(UsuarioController.getActiveUser()));
			
		} catch(ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ldao.close();
			tdao.close();
			udao.close();
		}
		
		response.sendRedirect("home");
	
	}

}
