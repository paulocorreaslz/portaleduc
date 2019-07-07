<%@ page contentType="text/html; charset=UTF-8" %>

<title>Listagem de estados</title>

<ul class="breadcrumb breadcrumb-wrapper">
       <li><a href="${pageContext.request.contextPath}/estado">Estados</a> <span class="divider">/</span></li>
   	<li class="active">Listagem</li>
</ul>

<h2>Listagem de estados</h2>

<table class="table table-striped">
	<thead>
		<tr>
			<th>Nome</th>
			<th>Sigla</th>
			<th>País</th>
			<th>Ações</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${page.content}" var="estado">
			<tr>
				<td>${estado.nome}</td>
				<td>${estado.sigla}</td>
				<td>${estado.pais}</td>
				<td width="120px">
					<a href="${pageContext.request.contextPath}/estado/${estado.id}" class="btn btn-small" title="Exibir"><i class="icon-file"></i></a>
					<a href="${pageContext.request.contextPath}/estado/${estado.id}/edita" class="btn btn-small" title="Editar"><i class="icon-pencil"></i></a>					
					<a href="" onclick="return confirmSubmitForm('Você tem certeza?', 'form_${estado.id}');" class="btn btn-small" title="Excluir"><i class="icon-trash"></i></a>					
					<form id="form_${estado.id}" class="hide" action="${pageContext.request.contextPath}/estado/${estado.id}" method="post">
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

<div id="pagination-links" class="pagination-right"></div>

<script type="text/javascript">
	$(document).ready(function() {
		$('#pagination-links').bootpag({
            total: ${page.totalPages},
            page: ${page.number + 1},
            maxVisible: 10,
            href: '${pageContext.request.contextPath}/estado/paginar/{{number}}'
        }).on("page", function(event, num){
             $("#content").html("Insert content");
        });
	});
</script>

<br />
<a href="${pageContext.request.contextPath}/estado/novo" class="btn"><i class="icon-plus"></i> Novo estado</a> 
<a href="${pageContext.request.contextPath}/estado/report" class="btn"><i class="icon-print"></i> Gerar relatório</a> 
