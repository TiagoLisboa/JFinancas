<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="
    	br.ifrn.poo.JFinancas.modelo.Limitador,
    	br.ifrn.poo.JFinancas.modelo.Teto,
    	br.ifrn.poo.JFinancas.modelo.Tipo,
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
	<title>Atualizar Limitador</title>
	<link href="./css/bootstrap.min.css" rel="stylesheet" type="text/css" />

	<link href="./fontawesome-free-5.0.1/web-fonts-with-css/css/fontawesome-all.min.css" rel="stylesheet">
	<link href="./css/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
<%
	Limitador limitador = (Limitador)request.getAttribute("limitador");
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	String ini = sdf.format(limitador.getInicio());
	String fim = sdf.format(limitador.getFim());
%>

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
			<h3>Atualizar Limitador</h3>
			<form action="updateLimitador" method="post">
			<input type="hidden" name="id" value="<%= limitador.getId() %>">
				<div class="form-group">
					<label for="datafim">Data Inicial</label>
					<input class="form-control" type="date" name="data1" id="dataini" value="<%= ini %>" required>
				</div>
				<div class="form-group">
					<label for="datafim">Data Final</label>
					<input class="form-control" type="date" name="data2" id="datafim" value="<%= fim %>" required>
				</div>
				<div class="form-group">
					<label for="valor">Valor</label>
					<input class="form-control" type="number" name="valor" step="any" id="valor" value="<%= limitador.getValor () %>" required>
				</div>
				
				<div class="form-group">
					<label for="nome">Nome</label>
					<input class="form-control" type="nome" name="nome" id="nome" value="<%= limitador.getNome () %>" required>
				</div>
				
				<div class="form-group">
					<label for="tipo">Tipo</label>
					<input name="tipo" list="tipoList" class="form-control" id="tipo" value="<%= limitador.getTipo() %>" autocomplete="off" required>
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
						<input class="form-check-input" type="radio" value="Ganho" name="categoria" <%= limitador instanceof Teto ? "checked" : "" %> required> Ganho
					</label>
					<label class="form-check-label">
						<input class="form-check-input" type="radio" value="Gasto" name="categoria" <%= ! (limitador instanceof Teto) ? "checked" : "" %> required> Gasto
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