<%@ page contentType="text/html; charset=UTF-8" %>

<title>Novo aluno</title>

<ul class="breadcrumb breadcrumb-wrapper">
       <li><a href="${pageContext.request.contextPath}/aluno">Alunos</a> <span class="divider">/</span></li>
   	<li class="active">Novo</li>
</ul>

<fieldset>
	<legend>Novo aluno</legend>
	<%@include file="form.jsp"%>
</fieldset>	
