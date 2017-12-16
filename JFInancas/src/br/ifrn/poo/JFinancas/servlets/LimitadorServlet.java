package br.ifrn.poo.JFinancas.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ifrn.poo.JFinancas.controle.UsuarioController;
import br.ifrn.poo.JFinancas.modelo.Meta;
import br.ifrn.poo.JFinancas.modelo.Teto;

/**
 * Servlet implementation class LimitadorServlet
 */
@WebServlet("/LimitadorServlet")
public class LimitadorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LimitadorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("novoLimitador.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
