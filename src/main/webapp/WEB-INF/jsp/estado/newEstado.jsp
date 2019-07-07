<%@ page contentType="text/html; charset=UTF-8" %>

<title>Novo estado</title>

<ul class="breadcrumb breadcrumb-wrapper">
       <li><a href="${pageContext.request.contextPath}/estado">Estados</a> <span class="divider">/</span></li>
   	<li class="active">Novo</li>
</ul>

<fieldset>
	<legend>Novo estado</legend>
	<%@include file="form.jsp"%>
</fieldset>	
