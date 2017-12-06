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
	
		
	<div class="tab-content">
		<form id="formCliente" name="formCliente" action="listarCliente" method="post" >
			<input type="hidden" name="flagBusca" id="flagBusca" value="${flagBusca}"/>
		    <fieldset class="container"> 
			    
				<ul class="nav nav-tabs" style="margin: 3% 0 3% 0;">
			   		<li class="active"><a data-toggle="tab" id="tabCliente" href="#cliente">Cliente</a></li>
				    <li class=""><a data-toggle="tab" id="tabAnimal" href="#animal">Animal</a></li>
			 	</ul>
		    
			    <div id="cliente" class="tab-pane fade in active">
					<div class="row">
						<div class="form-group col-md-6 col-sm-6">
							<label>Nome:</label>	
							<input type="text" id="nomeCliente" name="nomeCliente" class="form-control" value="${fc.nome}"/>
						</div>
				
						<div class="form-group col-md-6 col-sm-6">
							<label>Email:</label>	
							<input type="email" id="emailCliente" name="emailCliente" class="form-control" value="${fc.email}"/>
						</div>
				
						<div class="form-group col-md-4 col-sm-4">
							<label>Sexo:</label>	
							<select id="sexoCliente" name="sexoCliente" class="form-control">
								<option value="">Selecione</option>
								<option value="F" <c:if test= "${(fa.sexo eq 'F')}">selected</c:if>>Feminino</option>
								<option value="M" <c:if test= "${(fa.sexo eq 'M')}">selected</c:if>>Masculino</option>
							</select>
						</div>
									
						<div class="form-group col-md-4 col-sm-4">
							<label>CPF:</label>	
							<input type="text" id="cpfCliente" name="cpfCliente" class="form-control" value="${fc.cpf}"/>
						</div>
				
					</div>
				</div>
				
				<div id="animal" class="tab-pane fade">
					<div class="row">
									
						<div class="form-group col-md-4 col-sm-4">
							<label>Nome:</label>	
							<input type="text" id="nomeAnimal" name="nomeAnimal" class="form-control" value="${fa.nome}"/>
						</div>
				
						<div class="form-group col-md-4 col-sm-4">
							<label>Sexo:</label>	
							<select id="sexoAnimal" name="sexoAnimal" class="form-control">
								<option value="">Selecione</option>
								<option value="F" <c:if test= "${(fa.sexo eq 'F')}">selected</c:if>>Fêmea</option>
								<option value="M" <c:if test= "${(fa.sexo eq 'M')}">selected</c:if>>Macho</option>
							</select>
							</div>
				
						<div class="form-group col-md-4 col-sm-4">
							<label>Espécie:</label>	
							<input type="text" id="especieAnimal" name="especieAnimal" class="form-control" value="${fa.especie}"/>
						</div>
				
						<div class="form-group col-md-4 col-sm-4">
							<label>Raça:</label>	
							<input type="text" id="racaAnimal" name="racaAnimal" class="form-control" value="${fa.raca}"/>
						</div>
				
						<div class="form-group col-md-4 col-sm-4">
							<label>Idade:</label>	
							<input type="text" id="idadeAnimal" name="idadeAnimal" class="form-control" value="${fa.idade}"/>
						</div>
					</div>
				</div>	
			
				<div class="row">
					<div class="form-group col-md-12 col-sm-12">
						<input type="button" id="btnPesquisar" name="btnPesquisar" class="btn btn-primary" value="Pesquisar" />
						<input type="reset" id="btnLimpar" name="btnLimpar" class="btn btn-primary btlimpar" value="Limpar" />
						<input type="button" id="btnVoltar" name="btnVoltar" class="btn btn-primary" class="btvoltar" value="Voltar" />
					</div>
				</div>
			</fieldset>
		</form>
	</div>
	
	<div id="mensagens" class="msg">
		${msg}
	</div>
	
	<c:if test="${(not empty clientes)}">
	<div class="panel panel-primary" id="tableCliente">
    	<div class="panel-heading">Clientes</div>
		
		<table class="table table-striped" >
		  <thead>
		    <tr>
		      <th>Nome</th>
		      <th>Email</th>
		      <th>Sexo</th>
		      <th>cpf</th>
		      <th>Telefone</th>
		      <th>Alterar</th>
		      <th>Remover</th>
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
				    <td>
				    	<a href="#" class="btn btn-default btn-lg">
         					<span class="glyphicon glyphicon-pencil" style="color:darkcyan"></span>
        				</a>
        			</td>
				    <td>
				    	<a href="excluirCliente?id=${cliente.id}&flagBusca=C" class="btn btn-default btn-lg">
         					<span class="glyphicon glyphicon-remove" style="color:red"></span>
        				</a>
        			</td>
		    	</tr>
		    </c:forEach>
		  </tbody>
		</table>
	</div>
	</c:if>
	
	<c:if test="${(not empty animais)}">
		<div class="panel panel-primary" id="tableAnimal">
	    	<div class="panel-heading">Animais</div>
	    	
			<table class="table table-striped" >
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
					    <td>
				    	<a href="#" class="btn btn-default btn-lg">
         					<span class="glyphicon glyphicon-pencil" style="color:darkcyan"></span>
        				</a>
        				</td>
					      <td>
					    	<a href="excluirCliente?id=${animal.id}&flagBusca=A" class="btn btn-default btn-lg">
	         					<span class="glyphicon glyphicon-remove" style="color:red"></span>
	        				</a>
	        			</td>
			    	</tr>
			    </c:forEach>
			  </tbody>
			</table>
		</div>
	</c:if>
	
	<script type="text/javascript" src="../resources/js/script.js"></script>
	<script src="../resources/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>