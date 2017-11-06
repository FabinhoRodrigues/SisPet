<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<link rel="stylesheet" href="resources/css/estilo.css">
	<link href="https://fonts.googleapis.com/css?family=Wendy+One" rel="stylesheet">
	<link rel="stylesheet" href="resources/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
	
	<title>Login</title>
</head>
<body>
	
	<div id="mensagens" class="msg">
		${msg}
	</div>
	
	<div class="cadastroUsuarios">
	
		<div class="position-dog-img">	
		<div><img src="resources/img/dog-img.png"></div>
		Nao possui cadastro e é veterinário?<a href="cadastroVeterinario.jsp">Clique aqui</a>	
		</div>
	</div>
	
	<div class="container login-position">
		<form name="formLogin" action="login" method="post">
			<div class="form-group">
		    	<label for="login">Login:</label>
		    	<input type="login" class="form-control" name="login" id="login">
		  	</div>
		  	<div class="form-group">
			    <label for="pwd">Senha:</label>
			    <input type="password" class="form-control" name="senha" id="pwd">
			</div>
			<div class="checkbox">
		    	<label><input type="checkbox">Remember me</label>
		  	</div>
		  	<button type="submit" class="btn btn-default btn-login">Submit</button>
		</form>
	</div>

	<script type="text/javascript" src="resources/js/script.js"></script>
	<script src="resources/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>