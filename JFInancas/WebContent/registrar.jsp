<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>JFinances - Registro</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom & fontawesome-->
    <link href="fontawesome-free-5.0.1/web-fonts-with-css/css/fontawesome-all.min.css" rel="stylesheet">
    <link href="css/login.css" rel="stylesheet" type="text/css">

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
                <h1 class="p">Registrar Usuário</h1> 
                <hr>  
              </div>
              <div class="panel-body">
                <form action="registrar" method="POST">
                  <div class="form-group">
                    <div class="input-group">
                      <span class="input-group-addon"><i class="fa fa-user" aria-hidden="true"></i></span>
                      <label for="nome" class="sr-only">Usuário</label>
                      <input type="text" class="form-control" id="nome" name="nome" placeholder="Usuário" required>
                    </div>
                  </div>

                  <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-key" aria-hidden="true"></i></span>
                      <label for="passwd" class="sr-only">Senha</label>
                      <input type="password" class="form-control" id="passwd" name="passwd" placeholder="Senha" required>
                    </div>
                  </div>

                  <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fas fa-tag" aria-hidden="true"></i></span>
                      <label for="saldo" class="sr-only">Saldo Inicial</label>
                      <input type="number" class="form-control" id="saldo" name="saldo"placeholder="Saldo Inicial">
                    </div>
                  </div>
                  <hr>
                  <div class="input-group">
                    <button type="submit" class="btn btn-lg btn-light btn-block"><span>Cadastrar <i class="fas fa-sign-in-alt" aria-hidden="true"></i></span></button>
                  </div> 
                  <hr>
                </form>
                <div class="#">
                  <a href="/JFInancas/login" class="btn4">Login</a>
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
