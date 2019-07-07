<%@ page contentType="text/html; charset=UTF-8" %>

<title>Listagem de Declarações</title>

<h2>Listagem de declarações</h2>

<table class="table table-striped">
	<thead>
		<tr>
			<th>Turma</th>
			<th>Curso</th>
			<th>Tipo</th>
			<th>Data Inicial</th>
			<th>Data Final</th>
			<th>Ações</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${page.content}" var="professor">
			<tr>
				<td>${professor.turma.nome}</td>
				<td>${professor.turma.curso.nome}</td>
				<td>${professor.tipoProfessor}</td>
				<td>${professor.turma.getDataInicialFormatada()}</td>				
				<td>${professor.turma.getDataFinalFormatada()}</td>
				<th>
				<a href="${pageContext.request.contextPath}/turma/info/${professor.turma.id}" class="btn btn-small" title="Exibir Turma"><i class="icon-file"></i></a>
				<a href="${pageContext.request.contextPath}/certificado/aluno/${professor.certificado.id}" class="btn btn-small" title="Exibir Certificado"><i class="icon-certificate"></i></a>										
				</th>
								
			</tr>
		</c:forEach>
	</tbody>
</table>

<vrb:paginationLinks uri="/professor/paginarProfessores" page="${page}"/>
