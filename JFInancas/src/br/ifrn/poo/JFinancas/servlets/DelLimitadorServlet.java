package br.ifrn.poo.JFinancas.servlets;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ifrn.poo.JFinancas.DAO.LimitadorDAO;
import br.ifrn.poo.JFinancas.DAO.UsuarioDAO;
import br.ifrn.poo.JFinancas.controle.UsuarioController;
import br.ifrn.poo.JFinancas.exceptions.LoginOuSenhaIncorretoException;
import br.ifrn.poo.JFinancas.modelo.Teto;

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
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (UsuarioController.getActiveUser() != null) {
			int idx = Integer.parseInt(request.getParameter("idx"));
			
			LimitadorDAO ldao = new LimitadorDAO();
			ldao.deletar(new Teto(idx));
			UsuarioDAO udao = new UsuarioDAO();
			
			try {
				UsuarioController.setActiveUser(udao.login(UsuarioController.getActiveUser()));
			} catch (LoginOuSenhaIncorretoException | ParseException e) {
				e.printStackTrace();
			} finally {
				udao.close();
				ldao.close();
			}
			response.sendRedirect("Limitadores.jsp");
		} else {
			response.sendRedirect("login");
		}
	}


}
