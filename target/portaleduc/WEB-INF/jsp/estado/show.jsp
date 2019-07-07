<%@ page contentType="text/html; charset=UTF-8" %>

<title>Exibindo estado</title>

<ul class="breadcrumb breadcrumb-wrapper">
       <li><a href="${pageContext.request.contextPath}/estado">Estados</a> <span class="divider">/</span></li>
   	<li class="active">Exibir</li>
</ul>

<h2>Exibindo estado</h2>

<p>
	<strong>Nome:</strong>
	${estado.nome}
</p>
<p>
	<strong>Sigla:</strong>
	${estado.sigla}
</p>
<p>
	<strong>País:</strong>
	${estado.pais}
</p>

<p>
	<br/>
	<a href="${pageContext.request.contextPath}/estado/${estado.id}/edita" class="btn"><i class="icon-pencil"></i> Editar</a>
	<a href="${pageContext.request.contextPath}/estado" class="btn"><i class="icon-list"></i> Listagem</a>
</p>
