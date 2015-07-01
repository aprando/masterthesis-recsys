<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="<c:url value='/resources/bootstrap/img/favicon.ico' />">

    <title>Experimento de Mestrado - Alan Vidotti Prando</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value='/resources/bootstrap/css/bootstrap.min.css' />" rel="stylesheet">
    <!-- Bootstrap theme -->
    <link href="<c:url value='/resources/bootstrap/css/bootstrap-theme.min.css' />" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<c:url value='/resources/bootstrap/css/theme.css' />" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body role="document">
  
		<script>
			function statusChangeCallback(response) {
				if (response.status === 'connected') {
					FB.api('/me',function(response) {
						document.getElementById('status').innerHTML = 'Thanks for logging in, ' + response.name + '!';
					});
				} else if (response.status === 'not_authorized') {
					document.getElementById('status').innerHTML = 'Please log into this app.';
				} else {
					document.getElementById('status').innerHTML = 'Please log into Facebook.';
				}
			}
	
			function checkLoginState() {
				FB.getLoginStatus(function(response) {
					statusChangeCallback(response);
				});
			}
	
			window.fbAsyncInit = function() {
				FB.init({
					appId : '704203256284579',
					cookie : true, 
					xfbml : true,
					version : 'v2.0'
				});
	
				FB.getLoginStatus(function(response) {
					statusChangeCallback(response);
				});
	
			};
	
			// Load the SDK asynchronously
			(function(d, s, id) {
				  var js, fjs = d.getElementsByTagName(s)[0];
				  if (d.getElementById(id)) return;
				  js = d.createElement(s); js.id = id;
				  js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&appId=704203256284579&version=v2.0";
				  fjs.parentNode.insertBefore(js, fjs);
				}(document, 'script', 'facebook-jssdk'));
		
			
		</script>
	  

    <!-- Fixed navbar -->
    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Disserta&#231;&#227;o</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="#">Início</a></li>
            <li><a href="#about">Objetivo</a></li>
            <li><a href="#about">Contribui&#231;&#227;o</a></li>
            <li><a href="#about">Experimento</a></li>
            <li><a href="#contact">Contato</a></li>
          </ul>
        </div><!--/.nav-collapse -->        
      </div>
    </div>

    <div class="container theme-showcase" role="main">

      <div class="jumbotron">
        <h1>Big Data Recommender System</h1>
        <h2>Sistema de Recomenda&#231;&#227;o para E-commerce Utilizando Redes Sociais</h2>
        <p>Neste projeto de mestrado, ser&#225; desenvolvido um sistema de recomenda&#231;&#227;o para e-commerce, com o intuito de prover uma alternativa para o problema cold-start.<p>
        
      </div>

	
      <div class="page-header">
        <h1>Questoes</h1>
		
      </div>

      <div class="page-header">
      
        <h1>Redes Sociais</h1>
		
		<p>
			<div class="fb-login-button" data-max-rows="1" data-size="xlarge" data-show-faces="false" data-auto-logout-link="false"></div>
        </p>
        
      </div>


    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="<c:url value='/resources/bootstrap/js/bootstrap.min.js' />"></script>
    <!-- >script src="<c:url value='/resources/bootstrap/js/docs.min.js' />"></script -->
  </body>
</html>
