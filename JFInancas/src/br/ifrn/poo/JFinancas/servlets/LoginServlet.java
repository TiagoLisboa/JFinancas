package br.ifrn.poo.JFinancas.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ifrn.poo.JFinancas.controle.UsuarioController;
import br.ifrn.poo.JFinancas.exceptions.UsuarioNaoCadastradoException;
import br.ifrn.poo.JFinancas.modelo.Usuario;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	@Override
	protected void service (HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
		String nome = request.getParameter("usr");
		if (nome != null) {
			try {
				Usuario usr = UsuarioController.recuperarUsuario(nome);
				UsuarioController.setActiveUser(usr);
			} catch (UsuarioNaoCadastradoException e) {
				e.printStackTrace();
			}
		}
		response.sendRedirect("Usuario.jsp");
	}

}
