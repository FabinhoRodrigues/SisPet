<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
	<html>
		<head>
			<title>SisPet</title>
			<meta charset="utf-8">
		    <meta http-equiv="X-UA-Compatible" content="IE=edge">
		    <meta name="viewport" content="width=device-width, initial-scale=1">			
			<link rel="stylesheet" type="text/css" href="resources/slick/slick.css"/>
			<link rel="stylesheet" type="text/css" href="resources/slick/slick-theme.css"/>
			<link rel="stylesheet" href="resources/css/estilo.css">

			<link href="https://fonts.googleapis.com/css?family=Wendy+One" rel="stylesheet">
			<link rel="stylesheet" href="resources/font-awesome/css/font-awesome.min.css">
			<link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
					
			<script src="resources/js/jquery-2.1.4.min.js" type="text/javascript"></script>
		    <script src="resources/js/jquery-maskedinput/src/jquery.maskedinput.js" type="text/javascript"></script>
		    <script src="resources/js/jquery-validation/jquery.validate.js" type="text/javascript"></script>

		</head>
		<body>
				
				<div class="login" >
					<i class="fa fa-sign-in" aria-hidden="true"></i>
					<a href="login.jsp">Login ou cadastre-se</a>
				</div>
			<!-- NAVBAR -->

			<nav id="navbar">
					<a class="toggle-menu" href="login.jsp">&#9776;</a>
						<ul class="g1" id="menu">
							<li class="g2"><a href="#negocios">Conheça a Sispet</a></li>

							<li class="g2"><a href="sistema/cadastroCliente.jsp">Clientes</a>

							<li class="g2"><a href="#rodape">Faça Contato</a></li>	
						</ul>
																
			</nav>


		  <!-- BANNER -->

			<div class="banner">
				<div><img src="resources/img/main-banner.jpg"></div>
				<div><img class="position-banner02" src="resources/img/banner2.png"></div>				
			</div>

			<!-- BANNER DESCRIÃÃO -->

			<section id="negocios">
				<div class="container">
						<div class="container-2">
							<article>
								<h1>SisPet O Sistema de ajuda ao seu Pet</h1>
								<p>É um sistema desenvolvido para auxiliar todos os veterinários que procuram manter um registro de seus clientes organizados de forma prática</p>
								
								<p>O sistema visa a segurança das informações e uma praticidade de acesso, podendo ser acessado de qualquer lugar. </p>
							</article>							
						</div>											
				</div>
			</section>


			<!-- CONTATO -->
					
			<section id="rodape">
				<div class="container">
					<header class="header">
						<span class="bd left"></span>
						<span class="bd right"></span>
						<span class="bd bottom"></span>
						Envie-nos uma solicitaÃ§Ã£o, dÃºvida, sugestÃ£o ou crÃ­tica.<br>
						 Nossa equipe responderÃ¡ o contato o quanto antes
					</header>
					

					<article class="form">	
						<h3 class="title">Mande sua mensagem</h3>	
						<form action="#" method="post" style="width:80%;" class="form-horizontal">				
						<fieldset >
								<div class="form-group">		
									<label class="control-label col-sm-3" for="nome">Nome:</label>
									<div class="col-sm-9">
										<input type="text" name="nome" id="nome" class="val_campo form-control">
									</div>
								</div>
								<div class="form-group">		
									<label class="control-label col-sm-3" for="email">Email:</label>
									<div class="col-sm-9">
										<input type="text" name="email" id="email" class="val_campo form-control">
									</div>
								</div>
								<div class="form-group">		
									<label class="control-label col-sm-3" for="mensagem">Mensagem:</label>
									<div class="col-sm-9">
										<input type="text" name="mensagem" id="mensagem" class="val_campo form-control"
											style="height: 136px; font-size: 21pt;">
									</div>
								</div>
								<div class="form-group">        
    								<div class="col-sm-offset-2 col-sm-10">						
										<input type="submit"  value="enviar" class="btn enviar-btn">
									</div>
								</div>
						</fieldset>
					</article>	
				
			
			<!-- LOCAL -->

				<address class="address-style">
					<p>
						<strong>Rio de Janeiro</strong>
						<br>
						<span>
							Rua Ibituruna, 108 
							<br>
							Rio de Janeiro/ RJ - CEP 20271-020
							<br>
							Office: +55 (21) 9999-9999
						</span>
					</p>
				</address>	
				</div>
			</section>	

				<script type="text/javascript" src="resources/js/script.js"></script>
				<script type="text/javascript" src="resources/slick/slick.min.js"></script>
				<script src="resources/bootstrap/js/bootstrap.min.js"></script>	
				
				
			</body>	
</html>