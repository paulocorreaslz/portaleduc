<%@ page contentType="text/html; charset=UTF-8" %>

<title>Localizar cursos e turmas</title>

<ul class="breadcrumb breadcrumb-wrapper">
    <li><a href="${pageContext.request.contextPath}/inicio">Aluno</a> <span class="divider">/</span></li>
   	<li class="active">Turmas</li>
</ul>

<h2>Localizar Cursos e Turmas</h2>

<form class="form-inline" action="${pageContext.request.contextPath}/aluno/localizarCursos" method="post">
	<label>Matricula: </label>
	<input type="text" class="input-small" placeholder="matricula" name="matricula" >				
	<button type="submit" class="btn">Consultar</button>
</form>

