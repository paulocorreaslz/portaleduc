<%@ page contentType="text/html; charset=UTF-8" %>

<title>Listagem de alunos</title>

<ul class="breadcrumb breadcrumb-wrapper">
       <li><a href="${pageContext.request.contextPath}/alunos">Alunos</a> <span class="divider">/</span></li>
   	<li class="active">Listagem</li>
</ul>

<h2>Listagem de alunos</h2>

<form class="form-inline" action="${pageContext.request.contextPath}/aluno/busca" method="post">
	<input type="text" class="input-medium" placeholder="login" name="login" value="${user.login}"/>
	<input type="text" class="input-medium" placeholder="name" name="name" value="${user.name}"/>  	
  	<button type="submit" class="btn">Pesquisar</button>
</form>

<table class="table table-striped">
	<thead>
		<tr>
			<th>login</th>
			<th>name</th>
			<th>Ações</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${page.content}" var="user">
			<tr>
				<td>${user.login}</td>
				<td>${user.name}</td>
				<td class="actions">
					<a href="${pageContext.request.contextPath}/aluno/${user.id}" class="btn btn-small" title="Exibir"><i class="icon-file"></i></a>
					<a href="${pageContext.request.contextPath}/aluno/${user.id}/edita" class="btn btn-small" title="Editar"><i class="icon-pencil"></i></a>					
					<a href="" onclick="return confirmSubmitForm('Você tem certeza?', 'form_${artigo.id}');" class="btn btn-small" title="Excluir"><i class="icon-trash"></i></a>					
					<form id="form_${user.id}" class="hide" action="${pageContext.request.contextPath}/artigo/${user.id}" method="post">
						<input type="hidden" name="_method" value="delete"/>
					</form>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<div id="pagination-totals" class="left">
	<span class="label label-info">Total de itens encontrados: ${page.totalElements}</span>
</div>

<div id="pagination-links" class="pagination-right">
	<span class="label label-important">Não há itens a serem exibidos</span>
</div>

<script type="text/javascript">
		
	$(document).ready(function() {	
		var url = '${pageContext.request.contextPath}/aluno/paginar/{{number}}';
		<c:if test="${not empty user.login or not empty aluno.name}">
			url += buildParams('<c:out value="${user.login}" />', '<c:out value="${user.name}" />');		
		</c:if>

		<c:if test="${page.totalElements > 0}">
			$('#pagination-links').empty();
		</c:if>
		
		$('#pagination-links').bootpag({
            total: ${page.totalPages},
            page: ${page.number + 1},
            maxVisible: 10,
            href: url
        });
			
	});
		
	function buildParams(name, login) {
		var params = '';
		var hasParams = false;

		if (login!= '') {
			params += '?user.login=' + login;
			hasParams = true;
		} 
		
		if (name != '') {
			if (hasParams) {
				params += '&user.name=' + name;
			} else {
				params += '?user.name=' + name;
			}
		}
		
		return params;
	}
</script>

<br />

<a href="${pageContext.request.contextPath}/aluno/novo" class="btn"><i class="icon-plus"></i> Incluir aluno no curso</a> 
