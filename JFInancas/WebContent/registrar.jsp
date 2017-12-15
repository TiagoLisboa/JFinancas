<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Registrar Usuario</h2>
	<form action="registrar" method="POST">
		<div>
			<lable for="nome">Nome</lable>
			<input type="text" id="nome" name="nome"/>
		</div>
		<br />
		<div>
			<lable for="passwd">Senha</lable>
			<input type="password" id="passwd" name="passwd"/>
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
</body>
</html>