<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	
	 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 	 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  	 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		
	<title>Registros de Clientes</title>
	
<script type="text/javascript">
$(document).ready(function() {	
	$("#animal").hide();
	$("#tableAnimal").hide();
	
	
	$("#tabCliente").click(function(){
		$("#cliente").show();
		$("#animal").hide();
		
		$("#cliente :input").prop("disabled", false);
		$("#animal :input").prop("disabled", true);
		
		$("#tableCliente").show();
		$("#tableAnimal").hide();
	});
	
	$("#tabAnimal").click(function(){
		$("#animal").show();
		$("#cliente").hide();
		
		$("#animal :input").prop("disabled", false);
		$("#cliente :input").prop("disabled", true);
		
		$("#tableAnimal").show();
		$("#tableCliente").hide();
	});
	
	$("#btnPesquisar").click(function(){ 
		if($("#tabCliente").parent().attr("class") == "active"){
			$("#flagBusca").val("C");
		} else {
			$("#flagBusca").val("A");
		}
		
		$("#formCliente").submit();
	});
});
</script>
</head>
<body>
	
	<ul class="nav nav-tabs" style="width: 60%;	margin: 2% 0 1% 20%;">
   		<li class="active"><a data-toggle="tab" id="tabCliente" href="#cliente">Cliente</a></li>
	    <li class=""><a data-toggle="tab" id="tabAnimal" href="#animal">Animal</a></li>
 	</ul>
	
	<div class="tab-content">
		<form id="formCliente" name="formCliente" action="listarCliente" method="post" >
			<input type="hidden" name="flagBusca" id="flagBusca" />
		    <fieldset class="container"> 
			    <div id="cliente" class="tab-pane fade in active">
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
				
						<!-- 
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
				 		-->
					</div>
				</div>
				
				<div id="animal" class="tab-pane fade">
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
							<!-- 
						<div class="form-group col-md-4 col-sm-4">
							<label>Peso:</label>	
							<input type="text" id="pesoAnimal" name="pesoAnimal" class="form-control" value=""/>
						</div>
				
						<div class="form-group col-md-12 col-sm-12">
							<label>Observações:</label>	
							<textarea id="observacoesAnimal" name="observacoesAnimal" class="form-control" ></textarea>
						</div>
				 			-->
					</div>
				</div>	
			
				<div class="row container">
					<div class="form-group col-md-12 col-sm-12">
						<input type="button" id="btnPesquisar" name="btnPesquisar" class="btn btn-primary" value="Pesquisar" />
						<input type="button" id="btnLimpar" name="btnLimpar" class="btn btn-primary btlimpar" value="Limpar" />
						<input type="button" id="btnVoltar" name="btnVoltar" class="btn btn-primary btvoltar" value="Voltar" />
					</div>
				</div>
			</fieldset>
		</form>
	</div>
	
	<c:if test="${(not empty clientes)}">
		<table class="table table-striped" id="tableCliente">
		  <thead>
		    <tr>
		      <th>Nome</th>
		      <th>Email</th>
		      <th>Sexo</th>
		      <th>cpf</th>
		      <th>Telefone</th>
		    </tr>
		  </thead>
		  <tbody>
		  	<c:forEach var="cliente" items="${clientes}">
		    	<tr>
		      		<td>${cliente.nome}</td>
				    <td>${cliente.email}</td>
				    <td>${cliente.sexo}</td>
				    <td>${cliente.cpf}</td>
				    <td>${cliente.telefone}</td>
		    	</tr>
		    </c:forEach>
		  </tbody>
		</table>
	</c:if>
	
	<c:if test="${(not empty animais)}">
		<table class="table table-striped" id="tableAnimal">
		  <thead>
		    <tr>
		      <th>Nome</th>
		      <th>Sexo</th>
		      <th>Espécie</th>
		      <th>Raça</th>
		    </tr>
		  </thead>
		  <tbody>
		  	<c:forEach var="animal" items="${animais}">
		    	<tr>
		      		<td>${animal.nome}</td>
				    <td>${animal.sexo}</td>
				    <td>${animal.especie}</td>
				    <td>${animal.raca}</td>
		    	</tr>
		    </c:forEach>
		  </tbody>
		</table>
	</c:if>
	
	<script type="text/javascript" src="../resources/js/script.js"></script>
	<script src="../resources/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>