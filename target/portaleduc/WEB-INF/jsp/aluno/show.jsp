<%@ page contentType="text/html; charset=UTF-8" %>

<title>Exibindo Aluno</title>

<ul class="breadcrumb breadcrumb-wrapper">
       <li><a href="${pageContext.request.contextPath}/alunos">Alunos</a> <span class="divider">/</span></li>
   	<li class="active">Exibir</li>
</ul>

<h2>Exibindo aluno</h2>

<p>Matricula: ${aluno.matricula}</p>
<p>Nome: ${aluno.nome}</p>
<p>Lotação: ${aluno.lotacao}</p>
<p>Curso: ${aluno.turma.curso.nome}</p>
<p>Turma: ${aluno.turma.nome}</p>
<p>Situação: 
<c:if test="${aluno.situacao == 'A'}">
Aprovado
</c:if>
<c:if test="${aluno.situacao == 'R'}">
Reprovado
</c:if>
<c:if test="${aluno.situacao == 'D'}">
Desistiu
</c:if>
<c:if test="${aluno.situacao == 'E'}">
Evadiu
</c:if>

</p>


<p>
	<br/>
	
	<c:if test="${not empty aluno.turma.id}">				
		<a href="${pageContext.request.contextPath}/servidor/novoAluno/${aluno.turma.id}" class="btn"><i class="icon-plus"></i>Novo Aluno</a>
		<a href="${pageContext.request.contextPath}/aluno/turma/${aluno.turma.id}" class="btn"><i class="icon-list"></i> Listagem</a>	
	</c:if>
	
	<a href="${pageContext.request.contextPath}/aluno/${aluno.id}/edita" class="btn"><i class="icon-pencil"></i> Editar</a>	
</p>
