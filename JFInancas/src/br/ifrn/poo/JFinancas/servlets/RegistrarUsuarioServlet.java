package br.ifrn.poo.JFinancas.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ifrn.poo.JFinancas.DAO.UsuarioDAO;
import br.ifrn.poo.JFinancas.controle.UsuarioController;
import br.ifrn.poo.JFinancas.exceptions.UsuarioJaCadastradoExcpetion;
import br.ifrn.poo.JFinancas.modelo.Usuario;

/**
 * Servlet implementation class RegistrarUsuarioServlet
 */
@WebServlet("/registrar")
public class RegistrarUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrarUsuarioServlet() {
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
			request.getRequestDispatcher("registrar.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String saldo = request.getParameter("saldo");
		String passwd = request.getParameter("passwd");
		
		Usuario usuario = new Usuario(Float.parseFloat(saldo), nome, passwd);
		UsuarioDAO udao = new UsuarioDAO();
		
		try {
			udao.adiciona(usuario);
		} catch(UsuarioJaCadastradoExcpetion e) {
			request.setAttribute("jaCadastrado", true);
			request.getRequestDispatcher("registrar.jsp").forward(request, response);
			return;
		} finally {
			udao.close();
		}
		
		//UsuarioController.registrarUsuario(nome, Float.parseFloat(saldo), passwd);
		response.sendRedirect("login.jsp");
	}

}
