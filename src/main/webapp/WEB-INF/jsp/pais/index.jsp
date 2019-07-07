<%@ page contentType="text/html; charset=UTF-8" %>

<title>Listagem de países</title>

<ul class="breadcrumb breadcrumb-wrapper">
       <li><a href="${pageContext.request.contextPath}/pais">Países</a> <span class="divider">/</span></li>
   	<li class="active">Listagem</li>
</ul>

<h2>Listagem de países</h2>

<form class="form-inline" action="${pageContext.request.contextPath}/pais/busca" method="post">
	<input type="text" class="input-small" placeholder="Nome" name="pais.nome" value="${pais.nome}">
  	<input type="text" class="input-mini" placeholder="Sigla" name="pais.sigla" value="${pais.sigla}">
  	<button type="submit" class="btn">Pesquisar</button>
</form>

<table class="table table-striped">
	<thead>
		<tr>
			<th>Nome</th>
			<th>Sigla</th>
			<th>Ações</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${page.content}" var="pais">
			<tr>
				<td>${pais.nome}</td>
				<td>${pais.sigla}</td>
				<td class="actions">
					<a href="${pageContext.request.contextPath}/pais/${pais.id}" class="btn btn-small" title="Exibir"><i class="icon-file"></i></a>
					<a href="${pageContext.request.contextPath}/pais/${pais.id}/edita" class="btn btn-small" title="Editar"><i class="icon-pencil"></i></a>					
					<a href="" onclick="return confirmSubmitForm('Você tem certeza?', 'form_${pais.id}');" class="btn btn-small" title="Excluir"><i class="icon-trash"></i></a>					
					<form id="form_${pais.id}" class="hide" action="${pageContext.request.contextPath}/pais/${pais.id}" method="post">
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
		var url = '${pageContext.request.contextPath}/pais/paginar/{{number}}';
		<c:if test="${not empty pais.nome or not empty pais.sigla}">
			url += buildParams('<c:out value="${pais.nome}" />', '<c:out value="${pais.sigla}" />');		
		</c:if>

		$('#pagination-links').bootpag({
            total: ${page.totalPages},
            page: ${page.number + 1},
            maxVisible: 10,
            href: url
        });
	});
		
	function buildParams(nome, sigla) {
		var params = '';
		var hasParams = false;

		if (nome != '') {
			params += '?pais.nome=' + nome;
			hasParams = true;
		} 
		
		if (sigla != '') {
			if (hasParams) {
				params += '&pais.sigla=' + sigla;
			} else {
				params += '?pais.sigla=' + sigla;
			}
		}
		
		return params;
	}
</script>

<br />

<a href="${pageContext.request.contextPath}/pais/novo" class="btn"><i class="icon-plus"></i> Novo país</a> 
