<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*,
    	br.ifrn.poo.JFinancas.controle.UsuarioController,
    	br.ifrn.poo.JFinancas.modelo.Usuario,
    	br.ifrn.poo.JFinancas.modelo.Movimentacao,
    	br.ifrn.poo.JFinancas.modelo.Ganho,
    	br.ifrn.poo.JFinancas.modelo.Limitador,
    	br.ifrn.poo.JFinancas.modelo.Teto,
    	java.text.SimpleDateFormat" 
%>
<%

Usuario usuario = (Usuario)request.getAttribute("usuario");

if (usuario == null ) { 
	response.sendRedirect("home");
} else {%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Título</title>
</head>
<body>


<%

int offset;
if (request.getParameter("offset") != null)
	 offset = Integer.parseInt(request.getParameter("offset"));
else
	offset = 0;

%>

<div style="display: table; width: 100%;">
	<div style="width: 80%; float: left">
	
		<%
		
		Calendar now = Calendar.getInstance();
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		%>
		
		<h2> <%= usuario.getNome() %> - R$<%= usuario.getSaldo() %></h2>
		<h2>
		<a href="Limitadores.jsp">Limitadores</a>
		</h2>		
		<table border="1" style="width: 100%;">
		<thead>
			<tr>
				<th></th>	
				<th>
				Domingo
				</th>
				<th>
				Segunda-feira
				</th>
				<th>
				Terça-feira
				</th>
				<th>
				Quarta-feira
				</th>
				<th>
				Quinta-feira
				</th>
				<th>
				Sexta-feira
				</th>
				<th>
				Sábado
				</th>
				<th></th>	
			</tr>
		</thead>
		<tbody>
		<tr>
			<td></td>
			
			<%
		
				String[] days = new String[7];
				int delta = -now.get(GregorianCalendar.DAY_OF_WEEK) + 1 + 7*offset; //add 2 if your week start on monday
				now.add(Calendar.DAY_OF_MONTH, delta );
				for (int i = 0; i < 7; i++)
				{
				    days[i] = format.format(now.getTime());
				    %>
				    
				    <td><%= days[i] %></td>
				    
				    <%
				    now.add(Calendar.DAY_OF_MONTH, 1);
				}
				
				%>
			
			
			
			
			<td></td>
			</tr>
			<%
				
			for (Limitador l : usuario.getRegistradora().getLimitadores()) {
				Calendar c = Calendar.getInstance ();
				c.setTime (l.getInicio());
				ArrayList<String> dias = new ArrayList<String>();
				while ( true ) {
					dias.add(format.format(c.getTime()));
					c.add (Calendar.DATE, 1);
					if (format.format(c.getTime()).equals(format.format(l.getFim())))
						break;
				}
				dias.add(format.format(c.getTime()));
				
			%>
			<tr>
			<td></td>
			
			<%
				now = Calendar.getInstance();
				days = new String[7];
				delta = -now.get(GregorianCalendar.DAY_OF_WEEK) + 1 + 7*offset; //add 2 if your week start on monday
				now.add(Calendar.DAY_OF_MONTH, delta );
				for (int i = 0; i < 7; i++)
				{
				    days[i] = format.format(now.getTime());
				    %>
				    
				    <% if (dias.contains(days[i])) {  %> 
				    <td  style="background-color: <%= l instanceof Teto ? "red" : "green" %>;" ><%= l.getNome() %> - <%= l.getTipo() %></td>
				    <% } else { %>
				    <td></td>
				    <% } %> 
				    
				    <%
				    now.add(Calendar.DAY_OF_MONTH, 1);
				}
				
				%>
			
			
			
			
			<td></td>
			</tr>
			<% } %>
			<tr>
				<td>
				<a href="?offset=<%= offset-1 %>"> < </a>
				</td>
				<%
				now = Calendar.getInstance();
				days = new String[7];
				delta = -now.get(GregorianCalendar.DAY_OF_WEEK) + 1 + 7*offset; //add 2 if your week start on monday
				now.add(Calendar.DAY_OF_MONTH, delta );
				for (int i = 0; i < 7; i++)
				{
				    days[i] = format.format(now.getTime());
				    %>
				    
				    <td> <%
				    for (Movimentacao m : usuario.getRegistradora().getMovimentacoes() ) {
				    	if (format.format(m.getData()).equals(days[i])) {
				    	%>
				    	
				    	<p style="color: <%= m instanceof Ganho ? "green" : "red" %>;"><%=m.getNome() %> - R$<%= m.getValor() %></p>
				    	
				    	<%
				    	}
				    }
				    %> </td>
				    
				    <%
				    now.add(Calendar.DAY_OF_MONTH, 1);
				}
				
				%>
				<td>
					<a href="?offset=<%= offset+1 %>"> > </a>
				</td>
			</tr>
			<tr><td></td>
				<% for(int i=0;i<7;i++){ %>
					<td>
						<a href="movimentacao">+</a>
					</td>
				<% } %>
				<td></td>
			</tr>
		</tbody>
		</table>
	</div>
	
</div>
<h4>Limitadores</h4>
<a href="Extrato.jsp"><h4>Gerar extrato</h4></a>
<table>
	<% for (Limitador l : usuario.getRegistradora().getLimitadores()) {
		float t = l.calcularTransacoes(usuario.getRegistradora().getMovimentacoes()); 
	%>
		<tr>
			<td style="width: 300px; background-color: <%= l instanceof Teto ? "red" : "green" %>;" > <%= l.getNome() + " - " + l.getTipo().getNome() %> </td> 
			<td><progress value="<%= t %>" max="<%= l.getValor() %>" /></td>
			<td><% if (t > l.getValor()) %><span style="color: red">X</span></td>
		</tr>
		
	<% } %>
</tabl>

</body>
</html>
<% } %>