package br.ifrn.poo.JFinancas.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ifrn.poo.JFinancas.controle.UsuarioController;
import br.ifrn.poo.JFinancas.exceptions.SenhaIncorretaException;
import br.ifrn.poo.JFinancas.exceptions.UsuarioNaoCadastradoException;
import br.ifrn.poo.JFinancas.modelo.Usuario;

/**
 * Servlet implementation class Abc
 */
@WebServlet(urlPatterns = {"/login", "/"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String nome = request.getParameter("usr");
		String passwd = request.getParameter("passwd");
		try {
			Usuario usr = UsuarioController.recuperarUsuario(nome, passwd);
			UsuarioController.setActiveUser(usr);
		} catch (UsuarioNaoCadastradoException e) {
			request.setAttribute("usuarioIncorreto", true);
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		} catch (SenhaIncorretaException e) {
			request.setAttribute("senhaIncorreta", true);
			request.setAttribute("usuarioTentado", nome);
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		response.sendRedirect("Usuario.jsp");
	}

}