<%@ page contentType="text/html; charset=UTF-8" %>

<title>Listagem de Servidores</title>

<ul class="breadcrumb breadcrumb-wrapper">
    <li><a href="${pageContext.request.contextPath}/servidores">Servidores</a> <span class="divider">/</span></li>
   	<li class="active">Listagem</li>  
</ul>

<p>Nome da turma: ${turma.nome}</p>

<h2>Listagem de servidores</h2>

<form class="form-inline" action="${pageContext.request.contextPath}/servidor/buscaprofessor" method="post">
	<input type="text" class="input-small" placeholder="nome" name="nome" value="${servidor.nome}">
	<input type="text" class="input-small" placeholder="matricula" name="matricula" value="${servidor.matricula}">
  	<input type="hidden" name="turmaId" value="${turma.id}"/>   
  	<button type="submit" class="btn">Pesquisar</button>
</form>

<table class="table table-striped">
	<thead>
		<tr>
			<th>ID</th>
			<th>Nome</th>
			<th>Matricula</th>
			<th>Lotação</th>
			<th>Ações</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${page.content}" var="servidor">
			<tr>
				<td>${servidor.id}</td>
				<td>${servidor.nome}</td>
				<td>${servidor.matricula}</td>
				<td>${servidor.lotacao}</td>				
				<td class="actions">					
					<a href="${pageContext.request.contextPath}/professor/novo/${servidor.id}/${turma.id}" class="btn btn-small" title="Cadastrar professor"><i class="icon-plus-sign"></i></a>
					<a href="${pageContext.request.contextPath}/turma/${servidor.id}" class="btn btn-small" title="Exibir"><i class="icon-file"></i></a>
					<a href="${pageContext.request.contextPath}/turma/${servidor.id}/edita" class="btn btn-small" title="Editar"><i class="icon-pencil"></i></a>					
					<a href="" onclick="return confirmSubmitForm('Você tem certeza?', 'form_${turma.id}');" class="btn btn-small" title="Excluir"><i class="icon-trash"></i></a>					
					<form id="form_${servidor.id}" class="hide" action="${pageContext.request.contextPath}/turma/${servidor.id}" method="post">
						<input type="hidden" name="_method" value="delete"/>
					</form>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<vrb:paginationLinks uri="/servidor/paginar" page="${page}" params="nome;matricula;turmaId" />

<a href="${pageContext.request.contextPath}/turma/${turma.id}" class="btn"><i class="icon-file"></i> ver turma</a> 
 
 
