<%@ page contentType="text/html; charset=UTF-8" %>

<title>Listagem de Certificados</title>

<h2>Listagem de certificados</h2>

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
		<c:forEach items="${page.content}" var="aluno">
			<tr>
				<td>${aluno.turma.nome}</td>
				<td>${aluno.turma.curso.nome}</td>
				<td>${aluno.situacao}</td>
				<td>${aluno.turma.getDataInicialFormatada()}</td>				
				<td>${aluno.turma.getDataFinalFormatada()}</td>
				<th>
				<a href="${pageContext.request.contextPath}/turma/info/${aluno.turma.id}" class="btn btn-small" title="Exibir Turma"><i class="icon-file"></i></a>
				<a href="${pageContext.request.contextPath}/certificado/aluno/${aluno.certificado.id}" class="btn btn-small" title="Exibir Certificado"><i class="icon-certificate"></i></a>										
				</th>
								
			</tr>
		</c:forEach>
	</tbody>
</table>

<vrb:paginationLinks uri="/aluno/paginarAlunos" page="${page}"/>
