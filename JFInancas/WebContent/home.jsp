<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*,
    	br.ifrn.poo.JFinancas.controle.UsuarioController,
    	br.ifrn.poo.JFinancas.modelo.Usuario" 
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1> Usuarios </h1>
	<form action="adicionaUsuario" method="POST">
		<div>
			<lable for="nome">Nome</lable>
			<input type="text" id="nome" name="nome"/>
		</div>
		<br />
		<div>
			<lable for="saldo">Saldo</lable>
			<input type="number" id="saldo" name="saldo" step="any"/>
		</div>
		<br />
		<div>
			<input type="submit">
		</div>
	</form>
	<%
	Usuario active = UsuarioController.getActiveUser();
	if (active != null) {
	%>
		<h2><%= active.getNome () %></h2>
	<% } %>
	<br />
	<table>
	<thead>
		<tr><th>Usuario</th><th>Saldo</th><th>login</th></tr>
	</thead>
	<tbody>
	<%
	ArrayList<Usuario> usuarios = UsuarioController.getUsuarios ();
	for (Usuario usr : usuarios) {
	%>
	<tr>
		<td><%= usr.getNome() %></td>
		<td>R$ <%= usr.getSaldo() %></td>
		<td> <a href="/JFInancas/login?usr=<%= usr.getNome() %>">Logar</a></td>
	</tr>
	<%
	}
	%>
	</tbody>
	</table>
</body>
</html>