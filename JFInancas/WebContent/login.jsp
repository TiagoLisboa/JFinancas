<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>JFinances - Login</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">

    <!-- Custom & fontawesome-->
    <link href="fontawesome-free-5.0.1/web-fonts-with-css/css/fontawesome-all.min.css"" rel="stylesheet">
    <link href="login.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}css/login.css"/>
	


    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
<body>
  <div class="container">
    <section>
      <div class="row">
        <div class="col-md-4"></div>
          <div class="col-md-4">
            <div class="panel panel-default">
              <div class="panel-heading">
                <h1 class="p">Login</h1> 
                <hr>  
              </div>
              <div class="panel-body">
                <form action="login" method="post">
                  <div class="form-group">
                  	<% if (request.getAttribute("loginOuSenhaIncorreto") != null) { %>
      					<p id="loginError" style="color: red">Login ou Senha incorretos</p>
    				<% } %>
                    <div class="input-group">
                      <span class="input-group-addon"><i class="fa fa-user" aria-hidden="true"></i></span>
                      <label for="inputUser" class="sr-only">Usuário</label>
                      <input type="text" class="form-control" id="inputUser" name="usr" placeholder="Usuário" required value="<%= request.getAttribute("usuarioTentado") != null ?  request.getAttribute("usuarioTentado") : ""%>"/>
						<%
                      		if (request.getAttribute("usuarioIncorreto") != null) { %>
                        	<p style="color: red">Usuario não encontrado</p>
                      	<% } %>
                    </div>
                  </div>

                  <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-key" aria-hidden="true"></i></span>
                      <label for="inputPassword" class="sr-only">Senha</label>
                      <input type="password" name="passwd" class="form-control" id="inputPassword" placeholder="Senha" required <%= request.getAttribute("senhaIncorreta") != null ? "autofocus" : ""  %> />
                    </div>
                  </div>
                  <hr>

                  <div class="input-group">
                    <button type="submit" class="btn btn-lg btn-light btn-block"><span>ACESSAR <i class="fas fa-sign-in-alt" aria-hidden="true"></i></span></button>
                  </div> 
                  <hr>
                </form>
                <div>
                  <a href="/JFInancas/registrar" class="btn4">Criar conta</a>
                </div>
              </div>
            </div>
          </div>
      </div>
    </section>
  </div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>

