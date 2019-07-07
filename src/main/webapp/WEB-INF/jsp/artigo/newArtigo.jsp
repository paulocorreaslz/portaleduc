<%@ page contentType="text/html; charset=UTF-8" %>

<title>Novo artigo</title>

<ul class="breadcrumb breadcrumb-wrapper">
       <li><a href="${pageContext.request.contextPath}/artigos">Artigos</a> <span class="divider">/</span></li>
   	<li class="active">Novo</li>
</ul>

<fieldset>
	<legend>Novo artigo</legend>
	<%@include file="form.jsp"%>
</fieldset>	
