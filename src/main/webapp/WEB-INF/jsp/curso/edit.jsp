<%@ page contentType="text/html; charset=UTF-8" %>

<title>Editando curso</title>

<ul class="breadcrumb breadcrumb-wrapper">
       <li><a href="${pageContext.request.contextPath}/cursos">Curso</a> <span class="divider">/</span></li>
   	<li class="active">Editar</li>
</ul>

<fieldset>
	<legend>Editando curso</legend>
	<%@include file="form.jsp"%>
</fieldset>
