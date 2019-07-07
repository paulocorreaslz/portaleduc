<%@ page contentType="text/html; charset=UTF-8" %>

<title>Exibindo Professor</title>

<ul class="breadcrumb breadcrumb-wrapper">
       <li><a href="${pageContext.request.contextPath}/professor">professores</a> <span class="divider">/</span></li>
   	<li class="active">Exibir</li>
</ul>

<h2>Exibindo Professor</h2>

<p>Matricula: ${professor.matricula}</p>
<p>Nome: ${professor.nome}</p>
<p>Lotação: ${professor.lotacao}</p>
<p>Curso: ${professor.turma.curso.nome}</p>
<p>Turma: ${professor.turma.nome}</p>
<p>Situação: ${professor.situacao}</p>


<p>
	<br/>
		<c:if test="${not empty professor.turma.id}">				
		<a href="${pageContext.request.contextPath}/servidor/novoProfessor/${professor.turma.id}" class="btn"><i class="icon-plus"></i>Novo Professor</a>
		<a href="${pageContext.request.contextPath}/professor/turma/${professor.turma.id}" class="btn"><i class="icon-list"></i> Listagem</a>	
	</c:if>
	
	<a href="${pageContext.request.contextPath}/professor/${professor.id}/edita" class="btn"><i class="icon-pencil"></i> Editar</a>
	
</p>
