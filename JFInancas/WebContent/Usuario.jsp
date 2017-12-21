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
    	java.math.BigDecimal" 
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
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-4">
				<%= UsuarioController.getActiveUser().getNome() %>
			</div>
			<div class="col-sm-4">
			</div>
			<div class="col-sm-4">
				<div class="btn-group" role="group">
					<a class="btn btn-default" href="Limitadores.jsp">Limitadores</a> 
					<a class="btn btn-default" href="Extrato.jsp">Gerar extrato</a> 
					<a class="btn btn-default" href="logout">Desconectar</a>
				</div> 
			</div>
		</div>
	</div>
	
</header>

<div class="container-fluid">
	<div class="row">
		<div class="col-sm-1 full-size fa-left">
			<a href="?offset=<%= offset-1 %>">
				<span class="fa fa-chevron-left fa-3 " aria-hidden="true"></span>
			</a>
		</div>
		<div class="col-sm-10">	
			<table class="table table-striped table-bordered">
				<thead class="thead-dark">
					<tr>
						<th>
						Domingo
						</th>
						<th>
						Segunda-feira
						</th>
						<th>
						Terça-feira
						</th>
						<th>
						Quarta-feira
						</th>
						<th>
						Quinta-feira
						</th>
						<th>
						Sexta-feira
						</th>
						<th>
						Sábado
						</th>	
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
						
						if ( format.format(c.getTime()).equals(format.format(l.getFim())) ) {
							dias.add(format.format(c.getTime()));
						}
						
						
						
					%>
					<tr>
					
					<%
						boolean opa = false;
						for (int i = 0; i < 7; i++) {
							%>
							
							<% if (!opa && dias.contains(days[i])) {   %> 
								<td colspan="<%= dias.size() - i %>" style="text-align: center; padding: 2px 10px;background-color: <%= l instanceof Teto ? "rgba(255,0,0,0.7)" : "rgba(0,255,0,0.7)" %>;"><%= l.getNome() %> - <%= l.getTipo() %></td>
								<% opa = true; i = dias.size() -1;} else { %>
								<td></td>
							<% } 
						}
						
						%>
					</tr>
					<% } %>
					<tr>
						<%
						
						for (int i = 0; i < 7; i++)
						{
								%>
								
								<td style="padding: 0;"> <%
								for (Movimentacao m : usuario.getRegistradora().getMovimentacoes() ) {
									if (format.format(m.getData()).equals(days[i])) {
									%>
									
									<p title="<%=m.getNome()%>" class="movimentacao" 
										style="background-color: <%= m instanceof Ganho ? "rgba(0,255,0,0.2)" : "rgba(255,0,0,0.2)" %>;">
										<%= m instanceof Ganho ? "+" : "-" %> R$  
										<% 
											BigDecimal bigd = new BigDecimal(m.getValor());
											bigd=bigd.setScale(2,BigDecimal.ROUND_HALF_EVEN);
									        out.println(bigd);
										%>
									</p>
									
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
<table>
	<% for (Limitador l : usuario.getRegistradora().getLimitadores()) {
		float t = l.calcularTransacoes(usuario.getRegistradora().getMovimentacoes()); 
	%>
		<tr>
			<td style="width: 300px; background-color: <%= l instanceof Teto ? "red" : "green" %>;" > <%= l.getNome() + " - " + l.getTipo().getNome() %> </td> 
			<td><progress value="<%= t %>" max="<%= l.getValor() %>" /></td>
			<td><% if (t > l.getValor()) %><span style="color: red">X</span></td>
		</tr>
		
	<% } %>
</tabl>

</body>
</html>
<% } %>
