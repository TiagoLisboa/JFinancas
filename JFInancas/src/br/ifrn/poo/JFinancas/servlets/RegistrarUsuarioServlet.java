package br.ifrn.poo.JFinancas.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ifrn.poo.JFinancas.DAO.UsuarioDAO;
import br.ifrn.poo.JFinancas.controle.UsuarioController;
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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("registrar.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nome = request.getParameter("nome");
		String saldo = request.getParameter("saldo");
		String passwd = request.getParameter("passwd");
		Usuario usuario = new Usuario(Float.parseFloat(saldo), nome, passwd);
		UsuarioDAO udao = new UsuarioDAO();
		udao.adiciona(usuario);
		udao.close();
		//UsuarioController.registrarUsuario(nome, Float.parseFloat(saldo), passwd);
		response.sendRedirect("login.jsp");
	}

}
