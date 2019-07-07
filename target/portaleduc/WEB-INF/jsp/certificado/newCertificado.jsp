<%@ page contentType="text/html; charset=UTF-8" %>

<title>Novo certificado</title>

<ul class="breadcrumb breadcrumb-wrapper">
       <li><a href="${pageContext.request.contextPath}/aluno">Certificado</a> <span class="divider">/</span></li>
   	<li class="active">Novo</li>
</ul>

<fieldset>
	<legend>Novo certificado</legend>
	<%@include file="form.jsp"%>
</fieldset>	
