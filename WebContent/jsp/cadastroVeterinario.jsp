<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">

		<script src="../resources/js/jquery-2.1.4.min.js" type="text/javascript"></script>
	    <script src="../resources/js/jquery-maskedinput/src/jquery.maskedinput.js" type="text/javascript"></script>
	    <script src="../resources/js/jquery-validation/jquery.validate.js" type="text/javascript"></script>
	    
		<link rel="stylesheet" href="../resources/css/estilo.css">
		<link href="https://fonts.googleapis.com/css?family=Wendy+One" rel="stylesheet">
		<link rel="stylesheet" href="../resources/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="../resources/bootstrap/css/bootstrap.min.css">
		
	</head>
	<body>
	
		<c:set var="url" value="cadVeterinario" />
		
		<c:if test="${op eq 'C'}">
			<c:set var="url" value="altVeterinario" />
			<!--<c:set var="btenviar" value="Alterar Cliente" />-->
		</c:if>
		
		<div id="mensagens" class="msg">
			${msg}
		</div>
			
		<div id="cadastro-veterinario-head"> 
			<h1>Cadastro de Veterinário(a)</h1>
		</div>
		<form id="formVeterinario" action="${url}" method="post" >
			<fieldset  class="container">
				<legend class="control-label">Login e Senha</legend>
					
				<div class="row">
						
					<div class="form-group col-md-6 col-sm-6">
						<label>Login:</label>	
						<input type="text" id="loginVet" name="loginVet" class="form-control" value="${vet.login}"/>
					</div>

					<div class="form-group col-md-6 col-sm-6">
						<label>Senha:</label>	
						<input type="password" id="senhaVet" name="senhaVet" class="form-control" value="${vet.senha}"/>
					</div>
				</div>
				
				<legend class="control-label">Dados Pessoais</legend>
	
				<div class="row">						
					<div class="form-group col-md-6 col-sm-6">	
						<label>Nome Completo:</label>	
						<input type="text" id="nomeVet" name="nomeVet" class="form-control" value="${vet.nome}"/>
					</div>
		
					<div class="form-group col-md-6 col-sm-6">
						<label>Email:</label>	
						<input type="email" id="emailVet" name="emailVet" class="form-control" value="${vet.email}"/>
					</div>

					<div class="form-group col-md-6 col-sm-6">
						<label>Especialidade:</label>	
						<input type="text" id="espVet" name="espVet" class="form-control" value="${vet.especialidade}"/>
					</div>
	
	
					<div class="form-group col-md-6 col-sm-6">
						<label>CPF:</label>	
						<input type="text" id="cpfVet" name="cpfVet" class="form-control" value="${vet.cpf}"/>
					</div>
	
					<div class="form-group col-md-6 col-sm-6">
						<label>Tel:</label>	
						<input type="text" id="telVet" name="telVet" class="form-control" value="${vet.telefone}"/>
					</div>
					
					<div class="form-group col-md-12 col-sm-12">
						<input type="submit" id="btnEnviar" name="btnEnviar" class="btn btn-primary" value="Enviar">
						<input type="button" id="btnLimpar" name="btnLimpar" class="btn btn-primary btlimpar" value="Limpar" />
						<input type="button" id="btnVoltar" name="btnVoltar" class="btn btn-primary btvoltar" value="Voltar" />
					</div>
				</div>
			</fieldset>
		</form>

		<script type="text/javascript" src="../resources/js/script.js"></script>
		<script src="../resources/bootstrap/js/bootstrap.min.js"></script>
	</body>
</html>