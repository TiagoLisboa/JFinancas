<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Login</h2>
	<form action="login" method="post">
		<label>
			Nome:
			<input type="text" name="usr" value="<%= request.getAttribute("usuarioTentado") != null ?  request.getAttribute("usuarioTentado") : ""%>"/>
			<%
			if (request.getAttribute("usuarioIncorreto") != null) { %>
				<p style="color: red">Usuario não encontrado</p>
			<% } %>
		</label>
		<br />
		<br />
		<br />
		<label>
			Senha:
			<input type="password" name="passwd" <%= request.getAttribute("senhaIncorreta") != null ? "autofocus" : ""  %> />
			<% if (request.getAttribute("senhaIncorreta") != null) { %>
				<p style="color: red">Senha incorreta</p>
			<% } %>
		</label>
		<br />
		<br />
		<br />
		<input type="submit">
	</form>
	<a href="/JFInancas/registrar">novo usuario</a>
</body>
</html>