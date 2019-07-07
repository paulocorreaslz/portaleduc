<%@ page contentType="text/html; charset=UTF-8" %>

<title>Listagem de Turmas</title>

<h2>Turmas com inscrições abertas</h2>

<table class="table table-striped">
	<thead>
		<tr>
			<th>Turma</th>
			<th>Curso</th>
			<th>Data Inicial</th>
			<th>Data Final</th>
			<th>Ações</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${turmaList}" var="turma">
			<tr>
				<td>${turma.nome}</td>
				<td>${turma.curso.nome}</td>				
				<td>${turma.dataInicialFormatada}</td>				
				<td>${turma.dataFinalFormatada}</td>
				<th>
					<a href="${pageContext.request.contextPath}/turma/inscricao/${turma.id}" class="btn" title="Exibir Turma"><i class="icon-ok"></i> Fazer Inscrição </a>													
				</th>
								
			</tr>
		</c:forEach>
	</tbody>
</table>

<vrb:paginationLinks uri="/aluno/paginarTurmas" page="${page}"/>
