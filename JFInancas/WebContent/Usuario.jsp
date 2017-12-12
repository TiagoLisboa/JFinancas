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
<form action="registrarMovimentacao">
	<label>
		Data:<br>
		<input type="date" name="data">
		</br>
	</label>
	<label>
		Valor:<br>
		<input type="number" name="valor">
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
		Tipo2:<br>
	<label>	
		<input type="radio" value="Ganho" name="tipo2"> Ganho
	</label>
	<label>
		<input type="radio" value="Gasto" name="tipo2"> Gasto
		<br>
	</label>	
	<input type="submit">
</form>
<table>
<thead>
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
</thead>
<tr>
</tr>
</table>

</body>
</html>