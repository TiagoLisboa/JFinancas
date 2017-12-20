package br.ifrn.poo.JFinancas.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ifrn.poo.JFinancas.DAO.TipoDAO;
import br.ifrn.poo.JFinancas.DAO.UsuarioDAO;
import br.ifrn.poo.JFinancas.controle.UsuarioController;
import br.ifrn.poo.JFinancas.exceptions.LoginOuSenhaIncorretoException;
import br.ifrn.poo.JFinancas.modelo.Usuario;
import br.ifrn.poo.JFinancas.modelo.Tipo;
/**
 * Servlet implementation class Abc
 */
@WebServlet({"/login"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isActiveUser = UsuarioController.getActiveUser() != null;
		
		request.setAttribute("isnotactive", !isActiveUser);

		if (isActiveUser)
			response.sendRedirect("home");
		else
			request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String nome = request.getParameter("usr");
		String passwd = request.getParameter("passwd");
		request.setAttribute("isnotactive", true);
		TipoDAO tdao = new TipoDAO();
		
		ArrayList<Tipo> tipos = tdao.getAll();
		UsuarioController.setTipos(tipos);
		
		Usuario usuario = new Usuario (0, nome, passwd);
		UsuarioDAO udao = new UsuarioDAO();
		
		try {
			usuario = udao.login(usuario);
			UsuarioController.setActiveUser(usuario);
		} catch (LoginOuSenhaIncorretoException e) {
			request.setAttribute("loginOuSenhaIncorreto", true);
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			udao.close();
			tdao.close();
		}
		response.sendRedirect("Usuario.jsp");
	}

}