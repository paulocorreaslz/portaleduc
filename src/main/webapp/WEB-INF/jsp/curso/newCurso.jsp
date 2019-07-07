<%@ page contentType="text/html; charset=UTF-8" %>

<title>Novo Curso</title>

<ul class="breadcrumb breadcrumb-wrapper">
       <li><a href="${pageContext.request.contextPath}/cursos">Cursos</a> <span class="divider">/</span></li>
   	<li class="active">Novo</li>
</ul>

<fieldset>
	<legend>Novo curso</legend>
	<%@include file="form.jsp"%>
</fieldset>	
