package br.ifrn.poo.JFinancas.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.ParseException;

import br.ifrn.poo.JFinancas.controle.UsuarioController;
import br.ifrn.poo.JFinancas.modelo.Gasto;

@WebServlet("/registrarMovimentacao")
public class RegistrarMovimentacaoServlet extends HttpServlet {
	@Override
	protected void service (HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
		
		try {
			String data = request.getParameter("data");
			float valor = Float.parseFloat(request.getParameter("valor"));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = sdf.parse(data);
			String nome = request.getParameter("nome");
			String tipo = request.getParameter("tipo");
			String tipo2 = request.getParameter("tipo2");
			if(tipo2.equals("Gasto")){
				UsuarioController.getActiveUser().getRegistradora().novaMovimentacao(new Gasto(startDate, valor, nome, tipo));
			}
		} catch(ParseException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/Usuario.jsp");
		rd.forward(request, response);
	}

}
