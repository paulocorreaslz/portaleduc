<%@ page contentType="text/html; charset=UTF-8" %>

<title>Listagem de Cursos</title>

<ul class="breadcrumb breadcrumb-wrapper">
    <li><a href="${pageContext.request.contextPath}/cursos">Cursos</a> <span class="divider">/</span></li>
   	<li class="active">Listagem</li>
</ul>

<h2>Listagem de cursos</h2>

<form class="form-inline" action="${pageContext.request.contextPath}/curso/busca" method="post">
	<input type="text" class="input-small" placeholder="nome" name="nome" value="${curso.nome}">
		<vrb:datetimepicker name="dataCadastro" value="${curso.dataCadastro}"  placeholder="Data de Cadastro"  />
  	<button type="submit" class="btn">Pesquisar</button>
</form>

<table class="table table-striped">
	<thead>
		<tr>
			<th>ID</th>
			<th>Nome</th>
			<th>Data de Cadastro</th>
			<th>Ações</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${page.content}" var="curso">
			<tr>
				<td>${curso.id}</td>
				<td>${curso.nome}</td>
				<td>${curso.dataCadastroFormatada}</td>
				<td class="actions">
					
					<a href="${pageContext.request.contextPath}/turmas/Curso/${curso.id}" class="btn btn-small" title="Exibir turmas"><i class="icon-list"></i></a>
					<a href="${pageContext.request.contextPath}/turma/novo/${curso.id}" class="btn btn-small" title="Nova Turma"><i class="icon-plus-sign"></i></a>
					<a href="${pageContext.request.contextPath}/curso/${curso.id}/edita" class="btn btn-small" title="Editar"><i class="icon-pencil"></i></a>					
					<a href="${pageContext.request.contextPath}/curso/${curso.id}" class="btn btn-small" title="Exibir"><i class="icon-file"></i></a>
					<a href="" onclick="return confirmSubmitForm('Você tem certeza?', 'form_${curso.id}');" class="btn btn-small" title="Excluir"><i class="icon-trash"></i></a>					
					<form id="form_${curso.id}" class="hide" action="${pageContext.request.contextPath}/curso/${curso.id}" method="post">
						<input type="hidden" name="_method" value="delete"/>
					</form>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<vrb:paginationLinks uri="/curso/paginar" page="${page}"></vrb:paginationLinks>

<a href="${pageContext.request.contextPath}/curso/novo" class="btn"><i class="icon-plus"></i> Novo Curso</a> 
<a href="${pageContext.request.contextPath}/curso/relatorioGeral" class="btn"><i class="icon-print"></i> Gerar relatório</a> 
 
