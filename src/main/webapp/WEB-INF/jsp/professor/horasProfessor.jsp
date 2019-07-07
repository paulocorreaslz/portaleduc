<%@ page contentType="text/html; charset=UTF-8" %>

<title>Exibindo Professor</title>

<ul class="breadcrumb breadcrumb-wrapper">
       <li><a href="${pageContext.request.contextPath}/professor">professores</a> <span class="divider">/</span></li>
   	<li class="active">Exibir</li>
</ul>

<h3>Resultado Professor</h3>

<p> Matricula: ${professor.matricula}</p>
<p> Nome: ${professor.nome}</p>

<c:forEach items="${resultados}" var="resultado">
<p>${resultado}</p>
</c:forEach>

<p>
	<br/>
	
	
</p>
