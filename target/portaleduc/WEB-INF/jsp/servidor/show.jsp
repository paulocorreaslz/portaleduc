<%@ page contentType="text/html; charset=UTF-8" %>

<title>Exibindo turma</title>

<ul class="breadcrumb breadcrumb-wrapper">
       <li><a href="${pageContext.request.contextPath}/turmas">turmas</a> <span class="divider">/</span></li>
   	<li class="active">Exibir</li>
</ul>

<h2>${turma.nome}</h2>

<p>${turma.cargaHoraria}</p>

<p>
	<br/>
	<a href="${pageContext.request.contextPath}/turma/${turma.id}/edita" class="btn"><i class="icon-pencil"></i> Editar</a>
	<a href="${pageContext.request.contextPath}/turmas" class="btn"><i class="icon-list"></i> Listagem</a>
</p>
