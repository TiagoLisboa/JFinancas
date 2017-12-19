package br.ifrn.poo.JFinancas.servlets;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ifrn.poo.JFinancas.DAO.UsuarioDAO;
import br.ifrn.poo.JFinancas.controle.UsuarioController;
import br.ifrn.poo.JFinancas.exceptions.LoginOuSenhaIncorretoException;
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
			Usuario usuario = new Usuario (0, nome, passwd);
			UsuarioDAO udao = new UsuarioDAO();
			usuario = udao.login(usuario);
			udao.close();
			UsuarioController.setActiveUser(usuario);
		} catch (LoginOuSenhaIncorretoException e) {
			request.setAttribute("loginOuSenhaIncorreto", true);
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("Usuario.jsp");
	}

}