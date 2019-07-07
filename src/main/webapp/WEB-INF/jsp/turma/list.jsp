<%@ page contentType="text/html; charset=UTF-8" %>

<title>Listagem de Turmas</title>

<ul class="breadcrumb breadcrumb-wrapper">
    <li><a href="${pageContext.request.contextPath}/turmas">Turmas</a> <span class="divider">/</span></li>
   	<li class="active">Listagem</li>
</ul>

<c:if test="${not empty curso.id}">	
<p>Nome do curso: ${curso.nome}</p>
</c:if>
 
<h2>Listagem de turmas</h2>

<form class="form-inline" action="${pageContext.request.contextPath}/turma/busca" method="post">
	<input type="text" class="input-small" placeholder="nome" name="nome" value="${turma.nome}">
	<input type="hidden" name="cursoId" value="${curso.id}"/>   
	<button type="submit" class="btn">Pesquisar</button>
</form>

<table class="table table-striped">
	<thead>
		<tr>
			<th>ID</th>
			<th>Nome</th>
			<th>Curso</th>
			<th>Data inicial</th>
			<th>Data final</th>
			<th>Ações</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${page.content}" var="turma">
			<tr>
				<td>${turma.id}</td>
				<td>${turma.nome}</td>
				<td>${turma.curso.nome}</td>
				<td>${turma.dataInicialFormatada}</td>
				<td>${turma.dataFinalFormatada}</td>				
				<td class="actions">
					<a href="${pageContext.request.contextPath}/aluno/turma/${turma.id}" class="btn btn-small" title="Listar alunos"><i class="icon-list"></i></a>
					<a href="${pageContext.request.contextPath}/professor/turma/${turma.id}" class="btn btn-small" title="Listar professores"><i class="icon-list"></i></a>
					<a href="${pageContext.request.contextPath}/turma/${turma.id}" class="btn btn-small" title="Exibir"><i class="icon-file"></i></a>
					<a href="${pageContext.request.contextPath}/turma/${turma.id}/edita" class="btn btn-small" title="Editar"><i class="icon-pencil"></i></a>					
					<a href="" onclick="return confirmSubmitForm('Você tem certeza?', 'form_${turma.id}');" class="btn btn-small" title="Excluir"><i class="icon-trash"></i></a>					
					<form id="form_${turma.id}" class="hide" action="${pageContext.request.contextPath}/turma/${turma.id}" method="post">
						<input type="hidden" name="_method" value="delete"/>
					</form>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<vrb:paginationLinks uri="/turma/paginar" page="${page}" params="cursoId"></vrb:paginationLinks>

<c:if test="${not empty turma.curso.id}">		
<a href="${pageContext.request.contextPath}/turma/novo/${turma.curso.id}" class="btn"><i class="icon-plus"></i> Nova turma</a>
</c:if>
 
<a href="${pageContext.request.contextPath}/turma/relTurmasGeral" class="btn"><i class="icon-print"></i> Gerar relatório</a> 
 
