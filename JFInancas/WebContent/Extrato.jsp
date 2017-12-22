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
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Gerar extrato</title>
	<link href="./css/bootstrap.min.css" rel="stylesheet" type="text/css" />

	<link href="./fontawesome-free-5.0.1/web-fonts-with-css/css/fontawesome-all.min.css" rel="stylesheet">
	<link href="./css/main.css" rel="stylesheet" type="text/css" />
	<link href="extrato.css" rel="stylesheet" type="text/css" />
</head>
<body>
  <header class="page-header">
    <nav id="navbar-example2" class="navbar navbar-light bg-light">
      <a class="navbar-brand" href="home">Home</a>
      <ul class="nav nav-pills">
        <li class="nav-item">
          <a class="nav-link" href="#">Desconectar <span class="fas fa-sign-out-alt" aria-hidden="true"></span></a>
        </li>
    </nav>
  </header>
<div class="section">
         <div class="container">
            <div class="row">
                <div class="col-md-12">
                        <h1 class="text-center">Extrato</h1>
                    </div>
                </div>
            </div>
        </div>
        
<div id="demo">
  
  <!-- Responsive table starts here -->
  <!-- For correct display on small screens you must add 'data-title' to each 'td' in your table -->
  <div class="table-responsive-vertical shadow-z-1">
  <!-- Table starts here -->
  <table id="table" class="table table-hover table-mc-light-blue">
      <thead class="thead-dark">
        <tr>
          <td colspan="2">SALDO INICIAL</td>
          <td><%= UsuarioController.getActiveUser().getSaldo()%></td>
        </tr>
        <tr>
          <th>Data</th>
          <th>Descrição</th>
          <th>Valor</th>
        </tr>
                <% float saldo=UsuarioController.getActiveUser().getSaldo(); 
  for(Movimentacao l: UsuarioController.getActiveUser().getRegistradora().getMovimentacoes()){
    saldo+=l instanceof Ganho? l.getValor():-l.getValor();
    %>
      </thead>
      <tbody>
        <tr style="background-color:<%= l instanceof Ganho? "green":"red" %>">
          <td data-title="ID"><%= format.format(l.getData())%></td>
          <td data-title="descricao"><%= l.getNome() %> - <%= l.getTipo()%></td>
          <td data-title="value"><%= l instanceof Ganho? "+":"-" %>
        R$<%= l.getValor()%></td>
        </tr>
      <%} %>
        </tbody>
        <tfoot>	
        	<tr>
          		<td colspan="2">SALDO FINAL</td>
          		<td><%= saldo %></td>
       		</tr>
       	</tfoot>
    </table>
  </div>
</body>
</html>
