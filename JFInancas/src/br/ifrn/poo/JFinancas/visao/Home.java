package br.ifrn.poo.JFinancas.visao;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet ("/home")
public class Home extends HttpServlet {
	protected void service(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
		
		
		
		
		PrintWriter out = response.getWriter ();
		
		out.println("<html>");
        out.println("<body>");
        out.println("<form action");
        
        out.println("</body>");
        out.println("</html>");
		
		
		
	}
}
