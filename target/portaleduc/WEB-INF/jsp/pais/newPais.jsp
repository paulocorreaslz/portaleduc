<%@ page contentType="text/html; charset=UTF-8" %>

<title>Novo país</title>

<ul class="breadcrumb breadcrumb-wrapper">
       <li><a href="${pageContext.request.contextPath}/pais">Países</a> <span class="divider">/</span></li>
   	<li class="active">Novo</li>
</ul>

<fieldset>
	<legend>Novo país</legend>
	<%@include file="form.jsp"%>
</fieldset>	
