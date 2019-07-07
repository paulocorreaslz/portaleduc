<%@ page contentType="text/html; charset=UTF-8" %>

<title>Listagem de Cursos</title>

<h2>Listagem de cursos</h2>

<input type="hidden" id="professor" name="professor" value=${professor.id} />
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
		<c:forEach items="${page.content}" var="turma">
			<tr>
				<td>${turma.nome}</td>
				<td>${turma.curso.nome}</td>
				<td>${turma.getDataInicialFormatada()}</td>				
				<td>${turma.getDataFinalFormatada()}</td>
				<th><a href="${pageContext.request.contextPath}/turma/info/${turma.id}" class="btn btn-small" title="Exibir"><i class="icon-file"></i></a>
			</th>				
			</tr>
		</c:forEach>
	</tbody>
</table>


<vrb:paginationLinks uri="/aluno/paginateTurmas/" page="${page}" params="professor"/>
