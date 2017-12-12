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

@WebServlet("/adicionaUsuario")
public class AddUsuarioServlet extends HttpServlet {
	@Override
	protected void service (HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String saldo = request.getParameter("saldo");
		if (nome != null || saldo != null) {
			UsuarioController.registrarUsuario(nome, Float.parseFloat(saldo));
		}
		response.sendRedirect("home.jsp");
	}

}
