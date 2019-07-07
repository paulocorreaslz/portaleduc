<%@ page contentType="text/html; charset=UTF-8" %>

<title>Listagem de Cursos</title>

<h2>Listagem de cursos</h2>

<input type="hidden" id="aluno" name="aluno" value=${aluno.id} />
<input type="hidden" id="matricula" name="matricula" value=${aluno.matricula} />
<br>
<label>Matricula: ${aluno.matricula}</label>
<label>Nome: ${aluno.nome}</label>
<table class="table table-striped">
	<thead>
		<tr>
			<th>Turma</th>
			<th>Curso</th>
			<th>Situação</th>
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
				<td>${aluno.situacao}</td>
				<td>${turma.getDataInicialFormatada()}</td>				
				<td>${turma.getDataFinalFormatada()}</td>
				<th>
				<a href="${pageContext.request.contextPath}/turma/${turma.id}" class="btn btn-small" title="Exibir"><i class="icon-file"></i></a>
				</th>
								
			</tr>
		</c:forEach>
	</tbody>
</table>


<vrb:paginationLinks uri="/aluno/paginateTurmas2/" page="${page}" params="aluno, matricula"/>
