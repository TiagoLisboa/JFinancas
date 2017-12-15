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
			<input type="text" name="usr" />
		</label>
		<label>
			Senha:
			<input type="password" name="passwd" />
		</label>
		<input type="submit">
	</form>
	<a href="/JFInancas/registrar">novo usuario</a>
</body>
</html>