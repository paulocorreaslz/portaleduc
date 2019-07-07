<%@ page contentType="text/html; charset=UTF-8" %>

<title>Editando país</title>

<ul class="breadcrumb breadcrumb-wrapper">
       <li><a href="${pageContext.request.contextPath}/pais">Países</a> <span class="divider">/</span></li>
   	<li class="active">Editar</li>
</ul>

<fieldset>
	<legend>Editando país</legend>
	<%@include file="form.jsp"%>
</fieldset>
