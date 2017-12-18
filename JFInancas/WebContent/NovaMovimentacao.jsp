<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="br.ifrn.poo.JFinancas.controle.UsuarioController"%>
    
<%
if (UsuarioController.getActiveUser() == null) { 
	response.sendRedirect("login");
} else {%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div style="width: 50%; float: left;">
		<h3>Movimentações</h3>
		<form action="movimentacao" method="POST">
			<label>
				Data:<br>
				<input type="date" name="data">
				</br>
			</label>
			<label>
				Valor:<br>
				<input type="number" name="valor" step="any">
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
</body>
</html>
<% } %>