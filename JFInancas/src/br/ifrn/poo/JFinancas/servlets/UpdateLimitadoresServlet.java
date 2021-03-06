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
import br.ifrn.poo.JFinancas.DAO.UsuarioDAO;
import br.ifrn.poo.JFinancas.DAO.TipoDAO;
import br.ifrn.poo.JFinancas.controle.UsuarioController;
import br.ifrn.poo.JFinancas.modelo.Limitador;
import br.ifrn.poo.JFinancas.modelo.Meta;
import br.ifrn.poo.JFinancas.modelo.Teto;
import br.ifrn.poo.JFinancas.modelo.Tipo;

/**
 * Servlet implementation class UpdateLimitadoresServlet
 */
@WebServlet("/updateLimitador")
public class UpdateLimitadoresServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateLimitadoresServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (UsuarioController.getActiveUser() != null) {
			int idx = Integer.parseInt(request.getParameter("idx"));
			Limitador limitador = UsuarioController.getActiveUser().getRegistradora().getLimitadores().get(idx);
			request.setAttribute("limitador", limitador);
			request.setAttribute("idx", idx);
			request.getRequestDispatcher("updateLimitador.jsp").forward(request, response);
		} else {
			response.sendRedirect("login");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		UsuarioDAO udao = new UsuarioDAO();
		LimitadorDAO ldao = new LimitadorDAO();
		TipoDAO tdao = new TipoDAO();
		
		int id = Integer.parseInt(request.getParameter("id"));
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
		
		try {
			Date dataInicial = sdf.parse(data1);
			Date dataFinal = sdf.parse(data2);
			
			
			if(categoria.equals("Teto")){
				ldao.editar(UsuarioController.getActiveUser().getRegistradora(), new Teto(nome, valor, dataInicial, dataFinal, tipo, id));
			} else {
				ldao.editar(UsuarioController.getActiveUser().getRegistradora(), new Meta(nome, valor, dataInicial, dataFinal, tipo, id));
			}
			
			UsuarioController.setActiveUser(udao.login(UsuarioController.getActiveUser()));
			
		} catch(ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ldao.close();
			udao.close();
			tdao.close();
		}
		
		response.sendRedirect("home");
	}

}
