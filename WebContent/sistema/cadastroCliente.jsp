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

		<form id="formCliente" name="formCliente" action="" method="post" >
			<fieldset class="container"> 
				<legend class="control-label">Cadastro de Cliente</legend>
				
				<div class="row">
					
					<div class="form-group col-md-6 col-sm-6">
						<label>Nome:</label>	
						<input type="text" id="nomeCliente" name="nomeCliente" class="form-control" value=""/>
					</div>

					<div class="form-group col-md-6 col-sm-6">
						<label>Email:</label>	
						<input type="email" id="emailCliente" name="emailCliente" class="form-control" value=""/>
					</div>

					<div class="form-group col-md-4 col-sm-4">
						<label>Sexo:</label>	
						<select id="sexoCliente" name="sexoCliente" class="form-control">
							<option value="">Selecione</option>
							<option value="F">Feminino</option>
							<option value="M">Masculino</option>
						</select>
					</div>
					
					<div class="form-group col-md-4 col-sm-4">
						<label>CPF:</label>	
						<input type="text" id="cpfCliente" name="cpfCliente" class="form-control" value=""/>
					</div>

					<div class="form-group col-md-4 col-sm-4">
						<label>Tel:</label>	
						<input type="text" id="telCliente" name="telCliente" class="form-control" value=""/>
					</div>

					<div class="form-group col-md-4 col-sm-4">
						<label>Endereço:</label>	
						<input type="text" id="enderecoCliente" name="enderecoCliente" class="form-control" value=""/>
					</div>			

					<div class="form-group col-md-4 col-sm-4">
						<label>Número:</label>	
						<input type="text" id="numeroEndCliente" name="numeroEndCliente" class="form-control" value=""/>
					</div>

					<div class="form-group col-md-4 col-sm-4">	
						<label>Complemento:</label>	
						<input type="text" id="complementoEndCliente" name="complementoEndCliente" class="form-control" value=""/>
					</div>

				</div>

				<legend class="control-label">Dados do Animal</legend>

				<div class="row">
					
					<div class="form-group col-md-4 col-sm-4">
						<label>Nome:</label>	
						<input type="text" id="nomeAnimal" name="nomeAnimal" class="form-control" value=""/>
					</div>

					<div class="form-group col-md-4 col-sm-4">
						<label>Sexo:</label>	
						<select id="sexoAnimal" name="sexoAnimal" class="form-control">
							<option value="">Selecione</option>
							<option value="F">Fêmea</option>
							<option value="M">Macho</option>
						</select>
					</div>

					<div class="form-group col-md-4 col-sm-4">
						<label>Espécie:</label>	
						<input type="text" id="especieAnimal" name="especieAnimal" class="form-control" value=""/>
					</div>

					<div class="form-group col-md-4 col-sm-4">
						<label>Raça:</label>	
						<input type="text" id="racaAnimal" name="racaAnimal" class="form-control" value=""/>
					</div>

					<div class="form-group col-md-4 col-sm-4">
						<label>Idade:</label>	
						<input type="text" id="idadeAnimal" name="idadeAnimal" class="form-control" value=""/>
					</div>

					<div class="form-group col-md-4 col-sm-4">
						<label>Peso:</label>	
						<input type="text" id="pesoAnimal" name="pesoAnimal" class="form-control" value=""/>
					</div>

					<div class="form-group col-md-12 col-sm-12">
						<label>Observações:</label>	
						<textarea id="observacoesAnimal" name="observacoesAnimal" class="form-control" ></textarea>
					</div>

				</div>

				<div class="row">
					<div class="form-group col-md-12 col-sm-12">
						<input type="submit" id="btnSalvar" name="btnSalvar" class="btn btn-primary" value="Salvar" />
						<input type="button" id="btnAlterar" name="btnAlterar" class="btn btn-primary" value="Alterar" />
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