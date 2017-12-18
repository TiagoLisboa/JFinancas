<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*,
    	br.ifrn.poo.JFinancas.controle.UsuarioController,
    	br.ifrn.poo.JFinancas.modelo.Ganho,
    	br.ifrn.poo.JFinancas.modelo.Movimentacao,
    	java.text.SimpleDateFormat" 
    	
    	
    	
%>
<% SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Extratos</title>
</head>
<body>
	<table>
	<tr>
	<td colspan="2">
	</td>
	<td>
	<%= UsuarioController.getActiveUser().getSaldo() %>
	</td>
	</tr>
	<% float saldo=UsuarioController.getActiveUser().getSaldo(); 
	for(Movimentacao l: UsuarioController.getActiveUser().getRegistradora().getMovimentacoes()){
		saldo+=l instanceof Ganho? l.getValor():-l.getValor();
		%>
		<tr style="background-color:<%= l instanceof Ganho? "green":"red" %>" >  
			<td>
			<%= format.format(l.getData())%>
			</td>
			<td>
			<%= l.getNome() %>
			- <%= l.getTipo()%>
				
			</td>
			<td>
			
				<%= l instanceof Ganho? "+":"-" %>
				R$<%= l.getValor()%>
			</td>
		</tr>
	<%} %>
	<tr>
		<td colspan="2">SALDO</td>
		<td>
		<%= saldo %>
		</td>
	</tr>
	</table>
	<a href="Usuario.jsp">Voltar</a>
</body>
</html>