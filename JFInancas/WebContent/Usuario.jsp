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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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

	<div style="width: 50%; float: left;">
		<h3>Movimentações</h3>
		<form action="registrarMovimentacao">
			<label>
				Data:<br>
				<input type="date" name="data">
				</br>
			</label>
			<label>
				Valor:<br>
				<input type="number" name="valor">
				<br>
			</label>
			<label>
				Nome:<br>
				<input type="text" name="nome">
				<br>
			</label>
			<label>
				Tipo:<br>
				<input type="text" name="tipo">
				<br>
			</label>
				Categoria:
			<label>	
				<input type="radio" value="Ganho" name="categoria"> Ganho
			</label>
			<label>
				<input type="radio" value="Gasto" name="categoria"> Gasto
				<br>
			</label>	
			<input type="submit">
		</form>
	</div>
	
	<div style="width: 50%; float: left">
	
		<%
		
		Calendar now = Calendar.getInstance();
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		%>
		
		<h2> <%= UsuarioController.getActiveUser().getNome() %> - R$<%= UsuarioController.getActiveUser().getSaldo() %></h2>
		
		
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
				
			for (Limitador l : UsuarioController.getActiveUser().getRegistradora().getLimitadores()) {
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
				    <td  style="background-color: <%= l instanceof Teto ? "red" : "green" %>;" ><%= l.getTipo() %></td>
				    <% } else { System.out.println(dias); System.out.println(days[i]);%>
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
		
				days = new String[7];
				delta = -now.get(GregorianCalendar.DAY_OF_WEEK) + 1 + 7*offset; //add 2 if your week start on monday
				now.add(Calendar.DAY_OF_MONTH, delta );
				for (int i = 0; i < 7; i++)
				{
				    days[i] = format.format(now.getTime());
				    %>
				    
				    <td> <%
				    for (Movimentacao m : UsuarioController.getActiveUser().getRegistradora().getMovimentacoes() ) {
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
		</tbody>
		</table>
	</div>

</div>

<hr />

<div style="display: table;">
	<div style="widht: 50%; float: left;">
		<h3>Limitadores</h3>
		<form action="registrarLimitador" method="post">
			<label>
				Data inicial:<br>
				<input type="date" name="data1">
				</br>
			</label>
			<label>
				Data final:<br>
				<input type="date" name="data2">
				</br>
			</label>
			<label>
				Valor:<br>
				<input type="number" name="valor">
				<br>
			</label>
			<label>
				Nome:<br>
				<input type="text" name="nome">
				<br>
			</label>
			<label>
				Tipo:<br>
				<input type="text" name="tipo">
				<br>
			</label>
				Categoria:
			<label>	
				<input type="radio" value="Teto" name="categoria"> Teto
			</label>
			<label>
				<input type="radio" value="Meta" name="categoria"> Meta
				<br>
			</label>	
			<input type="submit">
		</form>
	</div>
</div>

</body>
</html>