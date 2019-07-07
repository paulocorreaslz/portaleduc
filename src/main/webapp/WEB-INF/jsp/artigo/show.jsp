<%@ page contentType="text/html; charset=UTF-8" %>

<title>Exibindo artigo</title>

<ul class="breadcrumb breadcrumb-wrapper">
       <li><a href="${pageContext.request.contextPath}/artigos">artigos</a> <span class="divider">/</span></li>
   	<li class="active">Exibir</li>
</ul>

<h2>${artigo.titulo}</h2>

<p>${artigo.conteudo}</p>

<p>
	<br/>
	<a href="${pageContext.request.contextPath}/artigo/${artigo.id}/edita" class="btn"><i class="icon-pencil"></i> Editar</a>
	<a href="${pageContext.request.contextPath}/artigos" class="btn"><i class="icon-list"></i> Listagem</a>
</p>
