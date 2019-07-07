<%@ page contentType="text/html; charset=UTF-8" %>

<title>Validar Certificado</title>

<ul class="breadcrumb breadcrumb-wrapper">
    <li><a href="${pageContext.request.contextPath}/inicio">Certificado</a> <span class="divider">/</span></li>
   	<li class="active">Validação</li>
</ul>

<h2>Validar certificado</h2>

<form class="form-inline" action="${pageContext.request.contextPath}/certificado/busca" method="post">
	<input type="text" class="input-xxlarge" placeholder="codigo" name="codigo" >
	<button type="submit" class="btn">Validar</button>
</form>

