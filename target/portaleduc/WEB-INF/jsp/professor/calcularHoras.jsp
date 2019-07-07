<%@ page contentType="text/html; charset=UTF-8" %>

<title>Calculo de horas</title>

<ul class="breadcrumb breadcrumb-wrapper">
    <li><a href="${pageContext.request.contextPath}/inicio">Professor</a> <span class="divider">/</span></li>
   	<li class="active">Calculo de horas</li>
</ul>

<h2>Localizar Professor</h2>

<form class="form-inline" action="${pageContext.request.contextPath}/professor/calcularhorasprofessor" method="post">
	<label>Matricula: </label>
	<input type="text" class="input-small" placeholder="matricula" name="matricula" >				
	<button type="submit" class="btn">Consultar</button>
</form>

