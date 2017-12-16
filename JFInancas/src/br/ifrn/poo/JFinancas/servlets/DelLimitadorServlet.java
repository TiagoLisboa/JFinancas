package br.ifrn.poo.JFinancas.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ifrn.poo.JFinancas.controle.UsuarioController;
import br.ifrn.poo.JFinancas.modelo.Limitador;

/**
 * Servlet implementation class DelLimitadorServlet
 */
@WebServlet("/delLimitador")
public class DelLimitadorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelLimitadorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int idx = Integer.parseInt(request.getParameter("idx"));
		UsuarioController.getActiveUser().getRegistradora().getLimitadores().remove(idx);
		response.sendRedirect("Usuario.jsp");
	}


}
