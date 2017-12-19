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

import br.ifrn.poo.JFinancas.DAO.MovimentacaoDAO;
import br.ifrn.poo.JFinancas.controle.UsuarioController;
import br.ifrn.poo.JFinancas.modelo.Ganho;
import br.ifrn.poo.JFinancas.modelo.Gasto;
import br.ifrn.poo.JFinancas.modelo.Registradora;
import br.ifrn.poo.JFinancas.modelo.Tipo;

/**
 * Servlet implementation class MovimentacaoServlet
 */
@WebServlet("/movimentacao")
public class MovimentacaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovimentacaoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (UsuarioController.getActiveUser() != null)
			response.sendRedirect("NovaMovimentacao.jsp");
		else
			response.sendRedirect("login");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String data = request.getParameter("data");
			float valor = Float.parseFloat(request.getParameter("valor"));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dataMovimentacao = sdf.parse(data);
			String nome = request.getParameter("nome");
			Tipo tipo = UsuarioController.getTipos().get(Integer.parseInt(request.getParameter("tipo")));
			String categoria = request.getParameter("categoria");
			MovimentacaoDAO mdao = new MovimentacaoDAO();
			
			Registradora r = UsuarioController.getActiveUser().getRegistradora();
			
			if(categoria.equals("Gasto")){
				//UsuarioController.getActiveUser().getRegistradora().novaMovimentacao(new Gasto(dataMovimentacao, valor, nome, tipo));
				mdao.adiciona(r, new Gasto(dataMovimentacao, valor, nome, tipo));
			} else {
				mdao.adiciona(r, new Ganho(dataMovimentacao, valor, nome, tipo));
			}
			
			
			r.setMovimentacoes(mdao.getByIdRegistradora(r));
			
			mdao.close();
		} catch(ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		response.sendRedirect("Usuario.jsp");
	}	
}


