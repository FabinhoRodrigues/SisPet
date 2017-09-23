jQuery( document ).ready(function($) {

	var blogTitles = $('#negocios h2'),
		$sidebar = $('#negocios .sidebar');

	// Adicionar títulos dinamicamente
	$.each(blogTitles, function( k, v ) {
		$sidebar.append( '<span>' + $(v).text() + '</span>' );
	});

	// Ativar primeiro título
	$sidebar.find('span:first-child').addClass('active');

	// Iniciar slides
	$('.slider, #negocios .slider').slick();

	// Ao clicar nos botões do slide, mudar a cor do título
  	$('#negocios .slider').on('beforeChange', function(event, slick, currentSlide, nextSlide) {
  		$sidebar.find('span').removeClass('active');
  		$sidebar.find('span:nth-child('+ (currentSlide + 1) +')').addClass('active');
	});

	$('.toggle-menu').click(function(e){

		$(this).toggleClass('active');

		$('#navbar .g1').toggleClass('active');

		e.preventDefault();

	});

	$( document ).on('click', '.toggle-menu', function(e) {
		e.preventDefault();

		var $menu = $('#menu');

		if ( ! $menu.hasClass('active') ) {
			$menu.addClass('active').slideToggle(500);
		} else {
			$menu.removeClass('active').slideToggle(500);
		}
	});

	var $width = $(window).width();

	if ( $width <= 990 ) {
		var endereco = $('#rodape address p:first-child').html();

		$('#menu').append( '<li class="address">'+ endereco +'</li>' );
	}
 
});


