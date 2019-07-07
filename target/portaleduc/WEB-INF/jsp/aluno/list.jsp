<%@ page contentType="text/html; charset=UTF-8" %>

<title>Listagem de Alunos</title>

<ul class="breadcrumb breadcrumb-wrapper">
    <li><a href="${pageContext.request.contextPath}/alunos">Alunos</a> <span class="divider">/</span></li>
   	<li class="active">Listagem</li>
</ul>

<c:if test="${not empty turma.id}">
<p>Nome do Curso: ${turma.curso.nome}</p>
<p>Nome da turma: ${turma.nome}</p>
</c:if>
<h2>Listagem de alunos</h2>

<form class="form-inline" action="${pageContext.request.contextPath}/aluno/busca" method="post">
	<input type="text" class="input-small" placeholder="nome" name="nome" value="${aluno.nome}">
	<input type="text" class="input-small" placeholder="matricula" name="matricula" value="${aluno.matricula}">
	<input type="hidden" name="turmaid" value="${turma.id}"/>
  	<button type="submit" class="btn">Pesquisar</button>
</form>

<table class="table table-striped">
	<thead>
		<tr>
			<th>ID</th>
			<th>Nome</th>
			<th>Matricula</th>
			<th>Situação</th>			
			<th>Lotação</th>
			<th>Ações</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${page.content}" var="aluno">
			<tr>
				<td>${aluno.id}</td>
				<td>${aluno.nome}</td>
				<td>${aluno.matricula}</td>
				<td>${aluno.situacao}</td>				
				<td>${aluno.lotacao}</td>				
				<td class="actions">					
					<a href="${pageContext.request.contextPath}/aluno/${aluno.id}" class="btn btn-small" title="Exibir"><i class="icon-file"></i></a>
					<c:if test="${not empty turma.id}">
						<a href="${pageContext.request.contextPath}/aluno/${aluno.id}/edita" class="btn btn-small" title="Editar"><i class="icon-pencil"></i></a>						
						<a href="${pageContext.request.contextPath}/certificado/CertificadoAluno/${aluno.id}/${turma.id}" class="btn btn-small" title="Exibir"><i class="icon-certificate"></i></a>					
						<a href="" onclick="return confirmSubmitForm('Você tem certeza?', 'form_${aluno.id}');" class="btn btn-small" title="Excluir"><i class="icon-trash"></i></a>					
						<form id="form_${aluno.id}" class="hide" action="${pageContext.request.contextPath}/aluno/${aluno.id}" method="post">
							<input type="hidden" name="_method" value="delete"/>
						</form>
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<vrb:paginationLinks uri="/aluno/paginar" page="${page}" params="turmaid"></vrb:paginationLinks>

<c:if test="${not empty turma.id}">		
		<a href="${pageContext.request.contextPath}/turma/${turma.id}" class="btn"><i class="icon-file"></i>Ver Turma</a>		
		<a href="${pageContext.request.contextPath}/servidor/novoAluno/${turma.id}" class="btn"><i class="icon-plus"></i>Adicionar Aluno</a>
		<a href="${pageContext.request.contextPath}/aluno/novoAlunoExterno/${turma.id}" class="btn"><i class="icon-plus"></i> Adicionar Aluno Externo</a> 
		<a href="${pageContext.request.contextPath}/aluno/relAlunosTurma/${turma.id}" class="btn"><i class="icon-print"></i> Imprimir Alunos</a>
		<a href="${pageContext.request.contextPath}/aluno/relAlunosAprovadosTurma/${turma.id}" class="btn"><i class="icon-print"></i> Imprimir Alunos Aprovados</a>
		<a href="${pageContext.request.contextPath}/aluno/relAlunosReprovadosTurma/${turma.id}" class="btn"><i class="icon-print"></i> Imprimir Alunos Reprovados</a>
			
</c:if>
