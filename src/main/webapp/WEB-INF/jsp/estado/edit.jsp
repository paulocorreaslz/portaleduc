<%@ page contentType="text/html; charset=UTF-8" %>

<title>Editando estado</title>

<ul class="breadcrumb breadcrumb-wrapper">
       <li><a href="${pageContext.request.contextPath}/estado">Estados</a> <span class="divider">/</span></li>
   	<li class="active">Editar</li>
</ul>

<fieldset>
	<legend>Editando estado</legend>
	<%@include file="form.jsp"%>
</fieldset>
