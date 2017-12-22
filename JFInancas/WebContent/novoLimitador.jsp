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
	<title>Novo Limitador</title>
	<link href="./css/bootstrap.min.css" rel="stylesheet" type="text/css" />

	<link href="./fontawesome-free-5.0.1/web-fonts-with-css/css/fontawesome-all.min.css" rel="stylesheet">
	<link href="./css/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<header class="page-header">
		<nav id="navbar-example2" class="navbar navbar-light bg-light">
		  <a class="navbar-brand" href="home">Home</a>
		  <ul class="nav nav-pills">
		    <li class="nav-item">
		      <a class="nav-link" href="Extrato.jsp">Extrato</a>
		    </li>
		    <li class="nav-item">
		      <a class="nav-link" href="logout">Desconectar</a>
		    </li>
		</nav>
	</header>
	<div class="container">
		<div class="row">
			<div class="col-sm-4">
			<h3>Novo Limitador</h3>
			<form action="novoLimitador" method="post">
				<div class="form-group">
					<label for="datafim">Data Inicial</label>
					<input class="form-control" type="date" name="data1" id="dataini" required>
				</div>
				<div class="form-group">
					<label for="datafim">Data Final</label>
					<input class="form-control" type="date" name="data2" id="datafim" required>
				</div>
				<div class="form-group">
					<label for="valor">Valor</label>
					<input class="form-control" type="number" name="valor" step="any" id="valor" required>
				</div>
				
				<div class="form-group">
					<label for="nome">Nome</label>
					<input class="form-control" type="nome" name="nome" id="nome" required>
				</div>
				
				<div class="form-group">
					<label for="tipo">Tipo</label>
					<input name="tipo" list="tipoList" class="form-control" id="tipo" autocomplete="off" required>
					<datalist id="tipoList" required>
						<% int i = 0; for (Tipo t : UsuarioController.getTipos()) { %>
							<option data-value="<%= t.getNome() %>"><%= t.getNome() %></option>
						<% i++;} %>
					</datalist>
					<input  type="hidden" class="form-control" id="tipo-hidden">
				</div>
				
				<div class="form-check">
					Categoria <br />
					<label class="form-check-label">	
						<input checked class="form-check-input" type="radio" value="Ganho" name="categoria" required> Ganho
					</label>
					<label class="form-check-label">
						<input class="form-check-input" type="radio" value="Gasto" name="categoria" required> Gasto
						<br>
					</label>
				</div>	
				<input type="submit" class="btn btn-primary">
				<a style="float: right;" class="btn btn-primary" href="Usuario.jsp">Voltar</a>
			</form>
			</div>
		</div>
	</div>
</body>
</html>
<% } %>