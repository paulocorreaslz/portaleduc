<%@ page contentType="text/html; charset=UTF-8" %>

<title>Listagem de Certificados</title>

<ul class="breadcrumb breadcrumb-wrapper">
    <li><a href="${pageContext.request.contextPath}/Alunos">Alunos</a> <span class="divider">/</span></li>
   	<li class="active">Listagem</li>
</ul>

<h2>Listagem de certificados</h2>

<table class="table table-striped">
	<thead>
		<tr>
			<th>ID</th>
			<th>Matricula</th>
			<th>Nome</th>
			<th>Lotação</th>
			<th>Ações</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${page.content}" var="certificado">
			<tr>
				<td>${certificado.aluno.id}</td>
				<td>${certificado.aluno.nome}</td>
				<td>${certificado.aluno.matricula}</td>
				<td>${certificado.aluno.lotacao}</td>				
				<td class="actions">					
				<a href="${pageContext.request.contextPath}/certificado/CertificadoAluno/${certificado.aluno.id}/${certificado.turma.id}" class="btn btn-small" title="Exibir"><i class="icon-certificate"></i></a>					
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<vrb:paginationLinks uri="/certificado/paginar" page="${page}"></vrb:paginationLinks>
