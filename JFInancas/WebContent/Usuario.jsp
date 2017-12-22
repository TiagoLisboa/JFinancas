<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*,
    	br.ifrn.poo.JFinancas.controle.UsuarioController,
    	br.ifrn.poo.JFinancas.modelo.Usuario,
    	br.ifrn.poo.JFinancas.modelo.Movimentacao,
    	br.ifrn.poo.JFinancas.modelo.Ganho,
    	br.ifrn.poo.JFinancas.modelo.Limitador,
    	br.ifrn.poo.JFinancas.modelo.Teto,
    	java.text.SimpleDateFormat,
    	java.math.BigDecimal,
    	java.text.DateFormatSymbols" 
%>
<%

Usuario usuario = (Usuario)request.getAttribute("usuario");

if (usuario == null ) { 
	response.sendRedirect("home");
} else {



%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Página do usuário</title>
	
	<link href="./css/bootstrap.min.css" rel="stylesheet" type="text/css" />

	<link href="./fontawesome-free-5.0.1/web-fonts-with-css/css/fontawesome-all.min.css" rel="stylesheet">
	<link href="./css/main.css" rel="stylesheet" type="text/css" />
</head>
<body>


<%

int offset;
if (request.getParameter("offset") != null)
	 offset = Integer.parseInt(request.getParameter("offset"));
else
	offset = 0;


Calendar now = Calendar.getInstance();

SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

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

<div class="container-fluid">
	<div class="row">
		<div class="col-sm-1 full-size fa-left">
			<a href="?offset=<%= offset-1 %>">
				<span class="fa fa-chevron-left fa-3" aria-hidden="true"></span>
			</a>
		</div>
		<div class="col-sm-10">	
			<table class="table table-bordered">
				<thead class="thead-dark">
					<tr>
					<%
						DateFormatSymbols sym = new DateFormatSymbols();
						String dayNames[] = sym.getWeekdays();
						for (String s : dayNames) { 
							if (s != "") {
						%>
							
							<th>
								<%= s %> <br />
							</th>
							
					<%		
							}
						}
					%>
					</tr>
					<tr>
						<%
					
							String[] days = new String[7];
							int delta = -now.get(GregorianCalendar.DAY_OF_WEEK) + 1 + 7*offset; //add 2 if your week start on monday
							now.add(Calendar.DAY_OF_MONTH, delta );
							for (int i = 0; i < 7; i++)
							{
									days[i] = format.format(now.getTime());
									%>
									
									<th><%= days[i] %></th>
									
									<%
									now.add(Calendar.DAY_OF_MONTH, 1);
							}
							
						%>
					</tr>
				</thead>
				<tbody>
					<%
					now = Calendar.getInstance();
					delta = -now.get(GregorianCalendar.DAY_OF_WEEK) + 1 + 7*offset; //add 2 if your week start on monday
					now.add(Calendar.DAY_OF_MONTH, delta );
					
					for (Limitador l : usuario.getRegistradora().getLimitadores()) {
						Calendar c = Calendar.getInstance ();
						
						if (now.getTime().after(l.getInicio()))
							c.setTime (now.getTime());
						else
							c.setTime (l.getInicio());
						
						ArrayList<String> dias = new ArrayList<String>();
						for ( int j = 0; j < 7; j++ ) {
							if (c.getTime().after(l.getFim()))
								break;
							dias.add(format.format(c.getTime()));
							c.add (Calendar.DATE, 1);
						}
						
						if (dias.size() > 0) {
							if (Arrays.asList(days).contains(dias.get(0)) || Arrays.asList(days).contains(dias.get(dias.size()-1))) {
						
					%>
					<tr>
					
					<%
						boolean opa = false;
						for (int i = 0; i < 7; i++) {
							BigDecimal bigd = new BigDecimal(l.getValor());
							bigd=bigd.setScale(2,BigDecimal.ROUND_HALF_EVEN);
							%>
							
							<% if (!opa && dias.contains(days[i])) {   %> 
								<td colspan="<%= dias.size() %>" style="cursor: help; text-align: center; padding: 2px 10px;background-color: <%= l instanceof Teto ? "rgba(255,0,0,0.7)" : "rgba(0,255,0,0.7)" %>;" title="<%= l.getTipo() %> (R$ <%= bigd %>)"><%= l.getNome() %></td>
							<% opa = true; i += dias.size()-1;} else { %>
								<td></td>
							<% } 
						}
						
						%>
					</tr>
					<% 		}
						} 
					} %>
					<tr>
						<%
						
						for (int i = 0; i < 7; i++)
						{
								%>
								
								<td style="padding: 0;"> <%
								for (Movimentacao m : usuario.getRegistradora().getMovimentacoes() ) {
									if (format.format(m.getData()).equals(days[i])) {
									%>
									
									<div class="movimentacao" 
										style="background-color: <%= m instanceof Ganho ? "rgba(0,255,0,0.2)" : "rgba(255,0,0,0.2)" %>;">
										<span title="<%=m.getNome()%> - <%= m.getTipo() %>">
										<%= m instanceof Ganho ? "+" : "-" %> R$  
										<% 
											BigDecimal bigd = new BigDecimal(m.getValor());
											bigd=bigd.setScale(2,BigDecimal.ROUND_HALF_EVEN);
									        out.println(bigd);
										%>
										</span>
										<a title="excluir" href="movimentacao?delete=<%= m.getId() %>" style="color: red;">X</a>
									</div>
									
									<%
									}
								}
								%> 
								</td>
								
								<%
						}
						
						%>
						
					</tr>
					<tr>
						<% for (int i = 0; i < 7; i++) { %>
							<td style="padding: 0;">
								<a class="plus" href="movimentacao?data=<%= days[i] %>">
									+
								</a>
							</td>
						<% } %>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="col-sm-1 full-size fa-right">
			<a href="?offset=<%= offset+1 %>">
				<span class="fa fa-chevron-right fa-3" aria-hidden="true"></span>
			</a>
		</div>
	</div>	
</div>



<div class="container">
	<div class="row">
		<div class="col-sm-12">
			<h3>Limitadores <small style="float: right;"><a title="Novo Limitador" href="novoLimitador">+</a></small></h3>
		
			<% int idx = -1; for (Limitador l : UsuarioController.getActiveUser().getRegistradora().getLimitadores()) {
				idx++;
				float t = l.calcularTransacoes(usuario.getRegistradora().getMovimentacoes()); 
				BigDecimal bigd = new BigDecimal(l.getValor());
				bigd=bigd.setScale(2,BigDecimal.ROUND_HALF_EVEN);
			%>
			
			<div class="card">
				<div class="card-header" style="background-color: <%= l instanceof Teto ? "#dc3545" : "#28a745"  %>;">
					<%= l.getNome() + " - " + (l instanceof Teto ? "Teto" : "Meta")  + " (" + l.getTipo() + ") de R$ " + bigd + " entre " + format.format(l.getInicio()) + " e " + format.format(l.getFim())%>
					<span style="float: right;">
						<a href="delLimitador?idx=<%=l.getId()%>">Deletar</a>
						<a href="updateLimitador?idx=<%=idx%>">Editar</a>
					</span>
				</div>
				<div class="card-body">
					<div class="progress">
						<div class="progress-bar <%
							if ((t  / l.getValor())*100 > 100) {
								out.println("bg-success");
							} else if ((t  / l.getValor())*100 > 50) {
								out.println("bg-warning");
							} else {
								out.println("bg-danger");
							}
							%>" role="progressbar" 
							
						style="width: <%= (t  / l.getValor())*100 %>%"><%= (t  / l.getValor())*100 %>%</div>
					</div>
					
					<br />
					
					<table class="table">
						<thead>
							<tr><th>Transação</th> <th>Data</th> <th>Valor</th></tr>
						</thead>
						<tbody>
							<% for (Movimentacao mm : l.procurarTransacoes(usuario.getRegistradora().getMovimentacoes()) ) { %>
							<tr>
								<td>
									<%= mm.getNome() %>
								</td>
								<td>
									<%= format.format(mm.getData()) %>
								</td>
								<td style="color: <%= mm instanceof Ganho ? "rgba(0,255,0,0.7)" : "rgba(255,0,0,0.7)" %>;">
									<%= mm instanceof Ganho ? "+" : "-" %> R$  
									<% 
										bigd = new BigDecimal(mm.getValor());
										bigd=bigd.setScale(2,BigDecimal.ROUND_HALF_EVEN);
								        out.println(bigd);
									%>
								</td>
							</tr>
							<% } %>
						<tbody>
					</table>
				</div>
			</div>
			
			<br />
			
			
			<% } %>
		</div>
	</div>
</div>



</body>
</html>
<% } %>
