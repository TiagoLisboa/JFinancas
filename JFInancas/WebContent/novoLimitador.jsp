<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="br.ifrn.poo.JFinancas.controle.UsuarioController,
    br.ifrn.poo.JFinancas.modelo.Tipo"%>
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
			<form action="novoLimitador" method="post">
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
					<select name="tipo">
					<% int i = 0; for (Tipo t : UsuarioController.getTipos()) { %>
						<option value="<%= i %>"><%= t.getNome() %></option>
					<% i++;} %>
				</select>
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
</body>
</html>
<% } %>