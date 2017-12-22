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

import br.ifrn.poo.JFinancas.DAO.MovimentacaoDAO;
import br.ifrn.poo.JFinancas.DAO.TipoDAO;
import br.ifrn.poo.JFinancas.controle.UsuarioController;
import br.ifrn.poo.JFinancas.modelo.Ganho;
import br.ifrn.poo.JFinancas.modelo.Gasto;
import br.ifrn.poo.JFinancas.modelo.Registradora;
import br.ifrn.poo.JFinancas.modelo.Tipo;

/**
 * Servlet implementation class MovimentacaoServlet
 */
@WebServlet("/movimentacao")
public class MovimentacaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovimentacaoServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data = request.getParameter("data");
		String idDelete = request.getParameter("delete");
		
		if (idDelete != null) {
			Gasto g = new Gasto(Integer.parseInt(idDelete));
			MovimentacaoDAO mdao = new MovimentacaoDAO();
			Registradora r = UsuarioController.getActiveUser().getRegistradora();
			mdao.delete(g);
			try {
				r.setMovimentacoes(mdao.getByIdRegistradora(r));
			} catch (ParseException e) {
				e.printStackTrace();
			} finally {
				mdao.close();
				response.sendRedirect("home");
			}
		} else {
		
			if (data == null) {
				response.sendRedirect("home");
				return;
			}
				
			
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date d = sdf.parse(data);
				sdf = new SimpleDateFormat("yyyy-MM-dd");
				request.setAttribute("data", sdf.format(d));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			if (UsuarioController.getActiveUser() != null)
				request.getRequestDispatcher("NovaMovimentacao.jsp").forward(request, response);
			else
				response.sendRedirect("login");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MovimentacaoDAO mdao = new MovimentacaoDAO();
		TipoDAO tdao = new TipoDAO();
		
		String data = request.getParameter("data");
		float valor = Float.parseFloat(request.getParameter("valor"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nome = request.getParameter("nome");
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
		Registradora r = UsuarioController.getActiveUser().getRegistradora();
		
		try {
			Date dataMovimentacao = sdf.parse(data);
			
			if(categoria.equals("Gasto")){
				//UsuarioController.getActiveUser().getRegistradora().novaMovimentacao(new Gasto(dataMovimentacao, valor, nome, tipo));
				mdao.adiciona(r, new Gasto(dataMovimentacao, valor, nome, tipo));
			} else {
				mdao.adiciona(r, new Ganho(dataMovimentacao, valor, nome, tipo));
			}
			
			
			r.setMovimentacoes(mdao.getByIdRegistradora(r));
		} catch(ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mdao.close();
			tdao.close();
		}
		
		response.sendRedirect("Usuario.jsp");
	}	
}


