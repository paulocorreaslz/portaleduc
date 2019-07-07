<%@ page contentType="text/html; charset=UTF-8" %>

<title>Listagem de Professores</title>

<ul class="breadcrumb breadcrumb-wrapper">
    <li><a href="${pageContext.request.contextPath}/professor">Professores</a> <span class="divider">/</span></li>
   	<li class="active">Listagem</li>
</ul>

<c:if test="${not empty turma.id}">
<p>Nome da turma: ${turma.nome}</p>
</c:if>

<h2>Listagem de professores</h2>

<form class="form-inline" action="${pageContext.request.contextPath}/professor/busca" method="post">
	<input type="text" class="input-small" placeholder="nome" name="nome" value="${professor.nome}">
	<input type="text" class="input-small" placeholder="matricula" name="matricula" value="${professor.matricula}">
	<input type="hidden" name="turmaid" value="${turma.id}"/>
  	<button type="submit" class="btn">Pesquisar</button>
</form>

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
		<c:forEach items="${page.content}" var="professor">
			<tr>
				<td>${professor.id}</td>
				<td>${professor.nome}</td>
				<td>${professor.matricula}</td>
				<td>${professor.lotacao}</td>				
				<td class="actions">					
					<a href="${pageContext.request.contextPath}/professor/${professor.id}" class="btn btn-small" title="Exibir"><i class="icon-file"></i></a>
						<c:if test="${not empty turma.id}">			
							<a href="${pageContext.request.contextPath}/professor/${professor.id}/edita" class="btn btn-small" title="Editar"><i class="icon-pencil"></i></a>
							<a href="${pageContext.request.contextPath}/certificado/CertificadoProfessor/${professor.id}/${turma.id}" class="btn btn-small" title="Exibir"><i class="icon-certificate"></i></a>														
							<a href="" onclick="return confirmSubmitForm('Você tem certeza?', 'form_${professor.id}');" class="btn btn-small" title="Excluir"><i class="icon-trash"></i></a>					
							<form id="form_${professor.id}" class="hide" action="${pageContext.request.contextPath}/professor/${professor.id}" method="post">
								<input type="hidden" name="_method" value="delete"/>
							</form>
						</c:if>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<vrb:paginationLinks uri="/professor/paginar" page="${page}"></vrb:paginationLinks>
 
 
<c:if test="${not empty turma.id}">		
		<a href="${pageContext.request.contextPath}/turma/${turma.id}" class="btn"><i class="icon-file"></i>Ver Turma</a>	
		<a href="${pageContext.request.contextPath}/servidor/novoProfessor/${turma.id}" class="btn"><i class="icon-plus"></i>Novo Professor</a>
		<a href="${pageContext.request.contextPath}/professor/relProfessoresTurma/${turma.id}" class="btn"><i class="icon-print"></i> Imprimir Professores</a>
</c:if>
 