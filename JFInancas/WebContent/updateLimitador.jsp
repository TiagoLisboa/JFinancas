<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="
    	br.ifrn.poo.JFinancas.modelo.Limitador,
    	br.ifrn.poo.JFinancas.modelo.Teto,
    	br.ifrn.poo.JFinancas.controle.UsuarioController,
    	java.text.SimpleDateFormat"%>
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
<%
	Limitador limitador = (Limitador)request.getAttribute("limitador");
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	String ini = sdf.format(limitador.getInicio());
	String fim = sdf.format(limitador.getFim());
%>
<form action="updateLimitador" method="post">
			<input type="hidden" name="idx" value="${idx}">
			<label>
				Data inicial:<br>
				<input type="date" name="data1" value="<%= ini %>">
				</br>
			</label>
			<label>
				Data final:<br>
				<input type="date" name="data2" value="<%= fim %>">
				</br>
			</label>
			<label>
				Valor:<br>
				<input type="number" name="valor"  value="<%= limitador.getValor () %>" step="any">
				<br>
			</label>
			<label>
				Nome:<br>
				<input type="text" name="nome"  value="<%= limitador.getNome () %>">
				<br>
			</label>
			<label>
				Tipo:<br>
				<input type="text" name="tipo"  value="<%= limitador.getTipo ().getNome() %>">
				<br>
			</label>
				Categoria:
			<label>	
				<input type="radio" value="Teto" name="categoria" <%= limitador instanceof Teto ? "checked" : "" %>> Teto
			</label>
			<label>
				<input type="radio" value="Meta" name="categoria" <%= ! (limitador instanceof Teto) ? "checked" : "" %>> Meta
				<br>
			</label>	
			<input type="submit">
		</form>
		<a href="Limitadores.jsp">Voltar</a>
</body>
</html>
<% } %>