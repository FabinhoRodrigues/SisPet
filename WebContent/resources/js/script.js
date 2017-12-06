$(document).ready(function(){
  $(".banner").slick({ 
	  autoplay: true,
	  autoplaySpeed: 2000,
	  arrow: true
 }); 
});
  
  $("#cpfCliente").mask("999.999.999-99");
  $("#telCliente").mask("(99) 9999-9999");
  
  $("#cpfVet").mask("999.999.999-99");
  $("#telVet").mask("(99) 9999-9999");
  
  $(".sonums").mask("9?9999"); //depois da interrogação é tudo opcional
  
  /*Regras de validação dos campos do form CLIENTE - biblioteca JQUERY VALIDATION*/
  $("#formCliente").validate({ //usa o name dos campos do form
        rules: {
            nomeCliente:{required: true},
            emailCliente:{required:true},
            sexoCliente:{required:true},
            cpfCliente:{required:true},
            telCliente:{required: true},
            enderecoCliente:{required: true},
            numeroEndCliente:{required: true},            
            complementoEndCliente:{required: false},                        
            nomeAnimal:{required: true},
            sexoAnimal:{required:true},
            especieAnimal:{required:true},
            racaAnimal:{required:true},
            idadeAnimal:{required:false},
            pesoAnimal:{required:true},
            observacoesAnimal:{required:false}        
        },
        messages: {
        	nomeCliente:{required: "Informe o nome do cliente"},
            emailCliente:{required:"Informe o email do cliente"},
            sexoCliente:{required: "Informe o sexo do cliente"},
            cpfCliente:{required: "Informe o CPF do cliente"},
            telCliente:{required: "Informe o telefone do cliente"},
            enderecoCliente:{required: "Informe o endereço do cliente"},
            numeroEndCliente:{required: "Informe o numero do endereço do cliente"},                                    
            nomeAnimal:{required: "Informe o nome do animal"},
            sexoAnimal:{required: "Informe o sexo do animal"},
            especieAnimal:{required: "Informe espécie do animal"},
            racaAnimal:{required: "Informe a raça do animal"},
            idadeAnimal:{required: "Informe a idade do animal"},
            pesoAnimal:{required: "Informe o peso do animal"}          
        }
  });
  
  /*Regras de validação dos campos do form CLIENTE - biblioteca JQUERY VALIDATION*/
  $("#formVeterinario").validate({ //usa o name dos campos do form
        rules: {
            loginVet:{required: true},
            senhaVet:{required:true},
            nomeVet:{required:true},
            emailVet:{required:true},
            cpfVet:{required: true},
            telVet:{required: true}
        },
        messages: {
        	loginVet:{required: "Informe o login"},
            senhaVet:{required: "Informe a senha"},
            nomeVet:{required: "Informe o nome"},
            emailVet:{required: "Informe o email"},
            cpfVet:{required: "Informe o CPF"},
            telVet:{required: "Informe o telefone"}     
        }
  });
  
  $("#formLogin").validate({
	  rules: {
		  login:{required: true},
		  password:{required: true}
	  },
	 messages: {
		 login:{required: "Informe o login"},
		 password:{required: "Informe a senha"}
	 } 
  });

	/*Limpar campos de um form quando o usuário clicar no botão Limpar */    
    $(".btlimpar").click(function(){
    	$('form').each (function(){
    		  this.reset();
    	});
    });
	
	/* Ação do botão voltar */
    $(".btvoltar").click(function(){
		window.location.href='index.jsp';
	});
	


function isEmail(email) {
  var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
  return regex.test(email);
}










