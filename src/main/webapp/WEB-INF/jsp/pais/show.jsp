<%@ page contentType="text/html; charset=UTF-8" %>

<title>Exibindo país</title>

<ul class="breadcrumb breadcrumb-wrapper">
       <li><a href="${pageContext.request.contextPath}/pais">Países</a> <span class="divider">/</span></li>
   	<li class="active">Exibir</li>
</ul>

<h2>Exibindo país</h2>

<p>
	<strong>Nome:</strong>
	${pais.nome}
</p>
<p>
	<strong>Sigla:</strong>
	${pais.sigla}
</p>

<p>
	<br/>
	<a href="${pageContext.request.contextPath}/pais/${pais.id}/edita" class="btn"><i class="icon-pencil"></i> Editar</a>
	<a href="${pageContext.request.contextPath}/pais" class="btn"><i class="icon-list"></i> Listagem</a>
</p>
