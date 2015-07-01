<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<!--
	Overflow by HTML5 UP
	html5up.net | @n33co
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title>Big Data Recommender System</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		<!--[if lte IE 8]><script src="<c:url value='/resources/overflow/css/ie/html5shiv.js' />"></script><![endif]-->
		<script src="<c:url value='/resources/overflow/js/jquery.min.js' />"></script>
		<script src="<c:url value='/resources/overflow/js/jquery.scrolly.min.js' />"></script>
		<script src="<c:url value='/resources/overflow/js/jquery.poptrox.min.js' />"></script>
		<script src="<c:url value='/resources/overflow/js/skel.min.js' />"></script>
		<script src="<c:url value='/resources/overflow/js/init.js' />"></script>
		<script src="<c:url value='/resources/overflow/js/jquery.barrating.min.js' />"></script>
		
			<link rel="stylesheet" href="<c:url value='/resources/overflow/css/skel.css' />" />
			<link rel="stylesheet" href="<c:url value='/resources/overflow/css/style.css' />" />

			<!-- RATING USING STAR -- WORKING BUT TO SMALL 
			.rating .br-widget {
			    height: 24px;
			}
			
			.rating .br-widget a {
			    background: url("<c:url value='/resources/overflow/images/star.png' />");
			    width: 24px;
			    height: 24px;
			    display: block;
			    float: left;
			}
			
			.rating .br-widget a:hover,
			.rating .br-widget a.br-active,
			.rating .br-widget a.br-selected {
			    background-position: 0 24px;
			}	
			-->
			
		
		<style type="text/css">
			
			.rating .br-widget {
			    height: 25px;
			}
			
			.rating .br-widget a {
			    display: block;
			    width: 70px;
			    height: 40px;
			    float: left;
			    background-color: #e3e3e3;
			    margin: 1px;
			}
			
			.rating .br-widget a.br-active,
			.rating .br-widget a.br-selected {
			    background-color: #59a6d6;
			}
			
			.rating .br-widget .br-current-rating {
			    line-height: 1.5;
			    float: left;
			    padding: 0 20px 0 20px;
			    color: #646464;
			}				
		</style>
		
		<!--[if lte IE 8]><link rel="stylesheet" href="<c:url value='/resources/overflow/css/ie/v8.css' />" /><![endif]-->
	</head>
	<body>
		<div id="fb-root"></div>

		<script>
		(function(d, s, id) {
		  var js, fjs = d.getElementsByTagName(s)[0];
		  if (d.getElementById(id)) return;
		  js = d.createElement(s); js.id = id;
		  js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&appId=704203256284579&version=v2.0";
		  fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
		
		// This is called with the results from from FB.getLoginStatus().
	  	function statusChangeCallback(response) {
		    if (response.status === 'connected') {
		    	salvarUsuarioFacebook();
		    } else if (response.status === 'not_authorized') {
		    	$('#statusFacebook').text('Por favor, realize o login com o Facebook autorizando a app Master Thesis.');
		    } else {
		    	$('#statusFacebook').text('Por favor, realize o login com o Facebook.');
		    }
		  }
		
		  function checkLoginState() {
		    FB.getLoginStatus(function(response) {
		      statusChangeCallback(response);
		    });
		  }
		
		  window.fbAsyncInit = function() {
			  FB.init({
			    appId      : '704203256284579',
			    cookie     : true,  
			    xfbml      : true,  
			    version    : 'v2.1' 
			  });
			
			  FB.getLoginStatus(function(response) {
			    statusChangeCallback(response);
			  });
		
		  };
		
		  // Here we run a very simple test of the Graph API after login is
		  // successful.  See statusChangeCallback() for when this call is made.
		  function salvarUsuarioFacebook() {
		    FB.api('/me', function(response) {
		    	$('#statusFacebook').text('Obrigado por realizar o login, ' + response.name + '!');
		      	
		        $("#nome").val(response.name);
		      	$("#email").val(response.email);
		      	$("#facebookId").val(response.id);
		      	
		      	salvarUsuario();
		      	
			    });
			  }
		  
		  function salvarUsuario() {
	    	  var data = $('#usuarioForm').serialize();
	    	  if($('#facebookId').val() || $('#twitterId').val()){
		      	  var url = $('#usuarioForm').attr('action');
		      	  $.ajax({
		      	    url: url,
		      	    data: data,
		      	    dataType:'json',
		      	    type:'POST', 
		      	    async:false, 
		      	    success: function(data) {
		      	    	$("#id").val(data.id);
		      	    	$("#idUsuarioQuestoes").val(data.id);
		      	    	$("#idUsuarioAvaliacao").val(data.id);
		      	    	if(data.twitterId != '' && data.facebookId != ''){
		      	    		$("#hrefProsseguirQuestoes").show()
		      	    	}else if(data.facebookId == ''){
		      	    		$("#msgFacebookObrigatorio").show()
		      	    	}else if(data.twitterId == ''){
		      	    		$("#msgTwitter").show()
		      	    	}
		      	    	//setTimeout(recuperarRecomendacoes(), 30000)
		      	    	recuperarRecomendacoes();
		      	    },
		      	    error: function(data) { 
		      	    	alert(data)
		    		}
		      	  });
	    	  }
		  }
		  
		  function salvarQuestionario() {
	    	  var data = $('#questionarioForm').serialize();
	    	  var url = $('#questionarioForm').attr('action');
	      	  $.ajax({
	      	    url: url,
	      	    data: data,
	      	    dataType:'json',
	      	    type:'POST', 
	      	    async:false, 
	      	    success: function(isRespostasOK) {
	      	    	if(isRespostasOK){
	      	    		$("#hrefProsseguirAvaliacoes").show()
	      	    		$("#msgRespostasObrigatorias").hide()
	      	    	}else{
	      	    		$("#msgRespostasObrigatorias").show()
	      	    	}
	      	    },
	      	    error: function(data) { 
	      	    	alert(data)
	    		}
	      	  });
		  }		  
		  
		  function salvarAvaliacoes() {
	    	  var data = $('#avaliacoesForm').serialize();
	    	  var url = $('#avaliacoesForm').attr('action');
	      	  $.ajax({
	      	    url: url,
	      	    data: data,
	      	    dataType:'json',
	      	    type:'POST', 
	      	    async:false, 
	      	    success: function(isRespostasOK) {
	      	    	$("#hrefFinalizar").show()
	      	    },
	      	    error: function(data) { 
	      	    	alert(data)
	    		}
	      	  });
		  }		  		  
		  

		  function recuperarRecomendacoes() {
			  var data = $('#usuarioForm').serialize();
	      	  $.ajax({
		      	    url: '<c:url value="/admin/usuarios/recuperar-recomendacoes" />',
		      	    data: data,
		      	    dataType:'json',
		      	    type:'POST', 
		      	    async:false, 
		      	    success: function(produtos) {
		      	    	for ( var i = 0, l = produtos.length; i < l; i++ ) {
		      	    		$( "#recomendacoes" ).append(replaceProduto(produtos[i], i));
		      	    	}
		      	    	$('[id^="rating-"]').barrating({ showSelectedRating:true });
		      	    },error: function(data) { 
		      	    	alert(data)
		    	 }});
		  	}

		  		  
		  
		function replaceProduto(produto, index) {
			try {
				var prodHtmlTemplateOld = '' 
					+'<article id="produto-{{id}}" class="container box style1 {{posicao}}" > 	'
					+'	<a href="#" class="image fit"><img src="{{imagemUrl}}" alt="" style="width:auto; height:300px" /></a> 	'
					+'	<div class="inner"> 													'
					+'		<header>															'
					+'			<h2>{{nome}}</h2>												'
					+'		</header>															'	
					+'		<p>{{descricao}}</p>												'
					+'	</div>																	'
					+'</article>																';
	
				var prodHtmlTemplate = ''
					+'<input type="hidden" name="avaliacoes[{{index}}].idProduto" value="{{id}}" />'
					+'<article id="produto-{{id}}" class="container box style1 right" > 		'
					+'	<a href="#"><img src="{{imagemUrl}}" alt=""								' 
					+'					style="width:250px; height:auto" align="right" /></a> 	'
					+'	<div> 																	'
					+'		<header>															'
					+'			<h2>{{nome}}</h2>												'
					+'		</header>															'	
					+'		<p>{{descricao}}</p>												'
					+'		<div class="rating" align="center" style="width: 700px;" \">		'
					+'		<select id="rating-{{id}}" name="avaliacoes[{{index}}].avaliacao">	'
					+'		    <option value="1">Nenhum Interesse!</option>					'
					+'		    <option value="2">Pouco Interesse!</option>						'
					+'		    <option value="3">Estou Interessado!</option>					'
					+'		    <option value="4">Muito interessado!</option>					'
					+'		    <option value="5">Vou comprar amanha!</option>					'
					+'		</select>															'
					+'		</div>																'
					+'	</div>																	'
					+'</article>																';
	
				
				var imagem = ((produto.imagemGrande) ? produto.imagemGrande : produto.imagem) ;
				var descricao = ((produto.descricaoLonga && produto.descricaoLonga.trim() != 'More information coming soon.') ? produto.descricaoLonga : produto.descricaoCurta);
				
				//Se nao possuir imagem ou descricao, pular imagem.
				if(descricao == null || descricao == '' || imagem == null || imagem == '')
					return ""
				
				if(descricao != null && descricao.length > 325)
					descricao = descricao.substring(0,325) +"...";
				
				prodHtml = prodHtmlTemplate;
				prodHtml = prodHtml.replace(/{{id}}/g, produto.id);
				prodHtml = prodHtml.replace(/{{imagemUrl}}/g, imagem);	
				prodHtml = prodHtml.replace(/{{nome}}/g, produto.nome);	
				prodHtml = prodHtml.replace(/{{descricao}}/g, descricao);
				prodHtml = prodHtml.replace(/{{index}}/g, index);
					
				return prodHtml;
			}catch(err) {
				console.log(err)
			    return "";
			} 			
		}
		
		 //$(function () {
		 //     $('[id^="rating-"]').barrating({ showSelectedRating:false });
		 //});		
		  
	</script>

	    <br/>

		<!-- Header -->
			<section id="header">
				<header>
					<h1>Big Data Recommender System</h1>
					<p>Um Sistema de Recomenda&#231;&#227;o para E-commerce Utilizando Redes Sociais em ambiente Big Data</p>
				</header>

				<footer>
					<a href="#banner" class="button style2 scrolly scrolly-centered">Iniciar!</a>
				</footer>
			</section>
		
		<!-- Banner -->
			<section id="banner">
				<header>
					<h2>Pesquisa Cient&#237;fica </h2>
				</header>
				<p>
				As redes sociais fazem parte da rotina das pessoas. <br />
				Mas o que acontece com os dados compartilhados? Likes, posts, fotos... ? <br />
				O que voc&#234; pensa sobre a utiliza&#231;&#227;o desses dados pelas empresas na web? <br />
				<br />
				Esse trabalho de mestrado pretende estudar a viabilidade do uso dessas informa&#231;&#245;es<br />
				sociais em e-commerces, com o objetivo de personalizar a experi&#234;ncia e satisfa&#231;&#227;o do usu&#225;rio.
				<br />
				Para isso, gostariamos da sua participa&#231;&#227;o no estudo, respondendo a um breve <br />
				question&#225;rio (n&#227;o deve demorar mais do que 5 minutos).
				<br />
				</p>
				
				<footer>
					<a href="#redes-sociais" class="button style2 scrolly" onmouseover='$("#redes-sociais").show()'>Quero Participar!</a>
					<!-- <a href="#o-projeto" class="button style2 scrolly">Mais sobre o projeto</a> -->
				</footer>
			</section>
		
			
			<article id="redes-sociais" class="container box style3"  style="display: none;">

				<form name="usuarioForm" id="usuarioForm" action="${pageContext.request.contextPath}/admin/usuarios/salvar-usuario-rede-social" method="POST">
				    <input type="hidden" name="id" id="id" />
				    <input type="hidden" name="nome" id="nome" /> 
				    <input type="hidden" name="email" id="email" />
				    <input type="hidden" name="sexo" id="sexo"/>
				    <input type="hidden" name="facebookId" id="facebookId" />
			
			
					<header>
						<h2>Redes Sociais</h2>
						<p>Primeiramente, se cadastre utilizando sua conta do Facebook e informe o seu usu&#225;rio no Twitter.</p>
					</header>
					<section>
						<table style="text-align: center; border: 1px">
							<tr>
								<td align="center" width="50%">
									<!-- <img class="image" src="<c:url value='/resources/overflow/images/flat-social-media-icons/facebook.png' />" alt="" title="Facebook" />
									<fb:login-button scope="public_profile,email,user_friends,user_about_me,user_actions.news,user_activities,user_interests,user_likes,user_religion_politics,user_status,read_stream" 
									onlogin="checkLoginState();"\>
									</fb:login-button> 
									-->
									<div class="fb-login-button"  data-max-rows="1" data-size="xlarge"
										data-show-faces="false" data-auto-logout-link="false"
										data-scope="public_profile,email,user_friends,user_about_me,user_actions.news,user_activities,user_interests,user_likes,user_religion_politics,user_status,read_stream"
										onlogin="checkLoginState();">
									</div>
									<div id="statusFacebook" style="font-size:14px;"></div>
									
								</td>
								<td align="center" width="50%">
									<div class="3u">
										<img class="image" src="<c:url value='/resources/overflow/images/flat-social-media-icons/twitter_2.png' />" alt="" title="Twitter" width="180" />
										<input type="text" name="twitterId" id="twitterId" onblur="javascript:salvarUsuario();" 
											style="border: 4px solid #F1B720; border-radius: 5px;  color: #333; font-size:14px; width: 180px"/>
									</div>
								</td>
							</tr>
						</table>
					</section>
					<footer> 
						<p style="display: none;" id="msgFacebookObrigatorio">O cadastro utilizando o Facebook &#233; obrigat&#243;rio para prosseguir.</p>
						<p style="display: none;" id="msgTwitter">N&#227;o possui twitter? Tudo bem! Clique <a href="#questoes"  onmouseover='$("#questoes").show()'>aqui</a></p>
						<a id="hrefProsseguirQuestoes" href="#questoes" class="button style1 scrolly" onmouseover='$("#questoes").show()' style="display: none">Seguir para as quest&#245;es!</a>
					</footer>
				</form>
			</article>		
		
			<article id="questoes" class="container box style3"  style="display: none;">
				<form name="questionarioForm" id="questionarioForm" action="${pageContext.request.contextPath}/formulario/salvar-questionario" method="POST">
				    <input type="hidden" name="idUsuario" id="idUsuarioQuestoes" />

					<header>
						<h2>Question&#225;rio</h2>
						<p>Por favor, responda as quest&#245;es abaixo.</p>
					</header>
					
					<c:forEach items="${questionario}" var="questao" varStatus="status">
						<input type="hidden" name="questoes[${status.index}].pergunta" value="${questao.pergunta}" />
						<input type="hidden" name="questoes[${status.index}].id" value="${questao.id}" />
						<section>
							<header>
								<h3>${questao.pergunta}</h3>
							</header>
							<c:forEach items="${questao.alternativas}" var="alternativa">
								<p><input type="radio" name="questoes[${status.index}].resposta" value="${alternativa}" /> ${alternativa}</p>
							</c:forEach>
						</section>
					</c:forEach>
	
					<footer>
						<p style="display: none;" id="msgRespostasObrigatorias">Responda todas as quest&#245;es para prosseguir.</p>
						<p><a id="hrefEnviarRespostas" href="javascript:void(0);" class="button style1 scrolly" onclick="javascript:salvarQuestionario();">Enviar Respostas!</a></p>
						<p><a style="display: none;" id="hrefProsseguirAvaliacoes" href="#avaliacoes-inicio" class="button style1 scrolly" onmouseover='$("#avaliacoes-inicio").show();$("#recomendacoes").show();$("#finalizar-formulario").show();'>Seguir para as avalia&#231;&#245;es</a></p>
					</footer>

				</form>
								
			</article>	
			
			
			<article id="avaliacoes-inicio" class="container box style2"  style="display: none;">
				<header>
					<h2>Avalia&#231;&#245;es </h2>
					<p>Para finalizar, d&#234; uma nota de 0 a 5 aos produtos abaixo, aonde 0 indica que voc&#234;  <br />
						n&#227;o possue interesse, e 5 indica que voc&#234; possue muito interesse. <br />
						Por favor, avalie o maior n&#250;mero de produtos poss&#237;veis!</p>
				</header>
			</article>			
				
			<form name="avaliacoesForm" id="avaliacoesForm" action="${pageContext.request.contextPath}/formulario/salvar-avaliacoes" method="POST">
				<input type="hidden" name="idUsuario" id="idUsuarioAvaliacao" />
				
				<div id="recomendacoes" style="display: none;">			
				</div>
			</form>
			
			<article id="finalizar-formulario" class="container box style2" style="display: none;">
				<footer>
					<p><a id="hrefEnviarAvaliacoes" href="javascript:void(0);" class="button style1 scrolly" onclick="javascript:salvarAvaliacoes();">Enviar Avalia&#231;&#245;es!</a></p>
					<p><a style="display: none;" id="hrefFinalizar"  href="#fim" class="button style scrolly" onmouseover="$('#fim').show()">Finalizar!</a></p>
				</footer>				
			</article>
		
		
			<article id="fim" class="container box style3"  style="display: none;">
				<header>
					<h2>Acabou!</h2>
					<p>Obrigado pela sua participa&#231;&#227;o.</p>
				</header>
				<!-- 
				<footer>
					<a href="#o-projeto" class="button style scrolly">Quero saber mais sobre o projeto!</a>
				</footer>
				 -->
			</article>
		
	
	        <!-- 
			<article class="container box style3" id="o-projeto">
				<header>
					<h2>Big Data Recommender System</h2>
					<p>Um Sistema de Recomenda&#231;&#227;o para e-commerce utilizando Redes Sociais</p>
				</header>
				
				<footer>
					<a style="align: center;" href="#redes-sociais" class="button style scrolly scrolly-centered">Quero Participar!</a >
				</footer>				
			</article>
		 -->	
		<section id="footer">
			
			<div class="copyright">
				<ul class="menu">
					<li>by Alan Vidotti Prando</li><li>Mestrando em Engenharia de Software no <a href="http://www.ipt.br">IPT</a></li>
				</ul>
			</div>
		</section>
		
		

	</body>
</html>