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
import br.ifrn.poo.JFinancas.modelo.Meta;
import br.ifrn.poo.JFinancas.modelo.Teto;
import br.ifrn.poo.JFinancas.modelo.Ganho;

@WebServlet("/registrarLimitador")
public class RegistrarLimitadorServlet extends HttpServlet {
	@Override
	protected void service (HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
		
		try {
			String data1 = request.getParameter("data1");
			String data2 = request.getParameter("data2");
			float valor = Float.parseFloat(request.getParameter("valor"));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dataInicial = sdf.parse(data1);
			Date dataFinal = sdf.parse(data2);
			String tipo = request.getParameter("tipo");
			String categoria = request.getParameter("categoria");
			if(categoria.equals("Teto")){
				UsuarioController.getActiveUser().getRegistradora().novoLimitador(new Teto(valor, dataInicial, dataFinal, tipo));
			} else {
				UsuarioController.getActiveUser().getRegistradora().novoLimitador(new Meta(valor, dataInicial, dataFinal, tipo));
			}
		} catch(ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("Usuario.jsp");
	}

}
