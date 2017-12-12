<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*,
    	br.ifrn.poo.JFinancas.controle.UsuarioController,
    	br.ifrn.poo.JFinancas.modelo.Usuario,
    	br.ifrn.poo.JFinancas.modelo.Movimentacao,
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

<a href="?offset=<%= offset-1 %>"> left </a>
<a href="?offset=<%= offset+1 %>"> right </a>

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
		Tipo2:<br> <!-- categoria -->
	<label>	
		<input type="radio" value="Ganho" name="tipo2"> Ganho
	</label>
	<label>
		<input type="radio" value="Gasto" name="tipo2"> Gasto
		<br>
	</label>	
	<input type="submit">
</form>
<%

Calendar now = Calendar.getInstance();

SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

%>


<table>
<thead>
	<tr>
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
	</tr>
</thead>
<tbody>
	<tr>
		<%

		String[] days = new String[7];
		int delta = -now.get(GregorianCalendar.DAY_OF_WEEK) + 1 + 7*offset; //add 2 if your week start on monday
		now.add(Calendar.DAY_OF_MONTH, delta );
		for (int i = 0; i < 7; i++)
		{
		    days[i] = format.format(now.getTime());
		    %>
		    
		    <td> <%
		    for (Movimentacao m : UsuarioController.getActiveUser().getRegistradora().getMovimentacoes() ) {
		    	if (format.format(m.getData()).equals(days[i])) {
		    	%>
		    	
		    	<%=m.getNome() %><br />
		    	
		    	<%
		    	}
		    }
		    %> </td>
		    
		    <%
		    now.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		%>
	</tr>
</tbody>
</table>

<script>

</script>
</body>
</html>