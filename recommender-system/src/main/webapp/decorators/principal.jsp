<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%
   response.setHeader("Cache-control", "no-cache, no-store");
   response.setHeader("Pragma", "no-cache");
   response.setHeader("Expires", "-1");
%>
<!DOCTYPE html>
<html lang="en">
<head>
 <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
 <meta charset="utf-8" />
 <meta name="viewport" content="width=device-width, initial-scale=1"/>
 <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
 <meta http-equiv="Pragma" content="no-cache" />
 <meta http-equiv="Expires" content="-1" />
 <meta http-equiv="Cache-Control" content="no-cache" />
 <title>Big Data Recommender System</title>
 <link rel="shortcut icon" href="<c:url value='/resources/bootstrap/images/ico/favicon.ico' />" />
 <link rel="stylesheet" type="text/css" href="<c:url value='/resources/bootstrap/css/bootstrap.min.css' />"/>
 <link rel="stylesheet" type="text/css" href="<c:url value='/resources/bootstrap/css/bootstrap-theme.min.css' />"/>
 <link rel="stylesheet" type="text/css" href="<c:url value='/resources/bootstrap/font-awesome/css/font-awesome.min.css' />"/>
 <link rel="stylesheet" type="text/css" href="<c:url value='/resources/bootstrap/css/jquery.dataTables.min.css' />"/>
 <!--[if IE 7]>
  <link rel="stylesheet" type="text/css" href="<c:url value='/resources/bootstrap/font-awesome/css/font-awesome-ie7.min.css' />"/>
  <link rel="stylesheet" type="text/css" href="<c:url value='/resources/bootstrap/font-awesome/css/font-awesome-ie7.min.css' />"/>
 <![endif]-->
 <!--[if lt IE 9]>
  <script type="text/javascript" src="<c:url value='/resources/bootstrap/js/html5shiv-3.7.2.js' />"></script>
  <script type="text/javascript" src="<c:url value='/resources/bootstrap/js/respond-1.4.2.min.js' />"></script>
 <![endif]-->
 <!-- Favicons -->
 <link rel="apple-touch-icon-precomposed" sizes="144x144" href="<c:url value='/resources/bootstrap/images/ico/apple-touch-icon-144x144.png' />">
 <decorator:getProperty property="page.style"/>
 <decorator:head/>
</head>
<body>
  <!-- Fixed navbar -->
    <nav class="navbar navbar-default" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#"><strong>Admin BDRS</strong></a>
        </div>
        
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li id="li_home"><a href="${pageContext.request.contextPath}/admin/">Home</a></li>
            <li id="li_usuarios"><a href="${pageContext.request.contextPath}/admin/usuarios/">Usu&aacute;rios</a></li>
            <li id="li_produtos"><a href="${pageContext.request.contextPath}/admin/produtos/">Produtos</a></li>
            <li id="li_categorias"><a href="${pageContext.request.contextPath}/admin/categorias/">Categorias</a></li>
            <li id="li_relatorios"><a href="${pageContext.request.contextPath}/admin/relatorios/">Relat&oacute;rios</a></li>
            <li id="li_receng"><a href="${pageContext.request.contextPath}/admin/recommendation-engine/">Recommendation Engine</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
    
  	<decorator:body/>

	<!-- >div class="clearBody"></div -->
  
	<script type="text/javascript" src="<c:url value='/resources/bootstrap/js/jquery-1.11.1.min.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/bootstrap/js/bootstrap.min.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/bootstrap/js/jquery.dataTables.min.js' />"></script>
	<!-- SCRIPT SITEMESH -->
	<decorator:getProperty property="page.local_script"></decorator:getProperty>
	
 </body>
</html>