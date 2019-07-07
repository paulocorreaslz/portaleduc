<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>

<!DOCTYPE html>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0">		
		<title><env:get key="app.name"/> - <decorator:title /></title>
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.ico" type="image/x-icon" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/application.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-responsive.css"/>
		<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
		<!--[if lt IE 9]>
		  <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->
  		<decorator:head/>
  		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
	</head>
	<body>		
		<div class="container container-blog">
	
		    <div class="navbar">
			    <div class="navbar-inner">
				    <a class="brand" href="${pageContext.request.contextPath}/">VRaptorBlog</a>
				    <ul class="nav">
				    <li class="active"><a href="${pageContext.request.contextPath}/">Artigos</a></li>
				    <li><a href="${pageContext.request.contextPath}/login">Administração</a></li>
				    </ul>
			    </div>
		    </div>
	
	      	<decorator:body/>
	
	      	<footer class="footer-wrapper">
	   			<hr>
	        	<p class="pull-right data"></p>
				<p>&copy; 2013 <a href="<env:get key="app.tre.url"/>" target="_blank"><env:get key="app.tre.acronym"/></a> - <env:get key="app.tre.name"/></p>
			</footer>
	
	    </div> <!-- /container -->
	  	
	  	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
	  	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.bootpag.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/application.js"></script>
		<script type="text/javascript">$("p.data").html(today());</script>
	</body>
</html>
