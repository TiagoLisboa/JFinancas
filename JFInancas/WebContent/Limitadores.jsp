<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*,
    	br.ifrn.poo.JFinancas.controle.UsuarioController,
    	br.ifrn.poo.JFinancas.modelo.Limitador,
    	br.ifrn.poo.JFinancas.modelo.Teto,
    	java.text.SimpleDateFormat" 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
		<%
		if (UsuarioController.getActiveUser() == null) { 
			response.sendRedirect("login");
		} else {
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		int pagina;
		if (request.getParameter("pagina") != null)
			pagina = Integer.parseInt(request.getParameter("pagina"));
		else
			pagina = 1;
		int qtdItens = 
				10;
		%>
		
		<%

		int offset;
		if (request.getParameter("offset") != null)
			 offset = Integer.parseInt(request.getParameter("offset"));
		else
			offset = 0;
		
		%>
		
		

	<div style="width: 100%; float: left">
	<a href="novoLimitador">Novo Limitador</a>
	<hr>
		<table>
		<thead>
			<tr>
			<th>
			Limitador
			</th>
			<th>
			Tipo
			</th>
			<th>
			Valor
			</th>
			<th>
			Inicio
			</th>
			<th>
			Fim
			</th>
			<th>Ações</th>
			</tr>
		</thead>
		<tbody>
		<% int idx = 0; for (Limitador l : UsuarioController.getActiveUser().getRegistradora().getLimitadores()) { 
				if(idx>=(pagina-1)*qtdItens && idx<pagina*qtdItens){
					
		%>
				
			<tr style="color: <%= !( l instanceof  Teto ) ? "green" : "red" %>;">

				<td><%= l.getNome() %></td>
				<td><%= l.getTipo() %></td>
				<td><%= l.getValor() %></td>
				<td><%= format.format(l.getInicio()) %></td>
				<td><%= format.format(l.getFim())%></td>
				<td>
				<a href="delLimitador?idx=<%=l.getId()%>">Deletar</a>
				<a href="updateLimitador?idx=<%=idx%>">Editar</a>
				</td>
			</tr>
		<%} idx++; } %>
		</tbody>
		</table>
		<% for(int i =0; i<1+UsuarioController.getActiveUser().getRegistradora().getLimitadores().size()/qtdItens;i++){%>
			<a href="?pagina=<%=i+1%>"><%=i+1%> </a>
		<%} %>

	</div>
			<a href="?pagina=<%= pagina-1 %>"> < </a>
			<a href="?pagina=<%= pagina+1 %>"> > </a>
			<br>
			<a href="Usuario.jsp"> Voltar </a>
</body>
</html>
<% } %>