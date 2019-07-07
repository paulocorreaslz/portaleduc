<%@ page contentType="text/html; charset=UTF-8" %>

<title>Listagem de artigos</title>

<ul class="breadcrumb breadcrumb-wrapper">
       <li><a href="${pageContext.request.contextPath}/artigos">Artigos</a> <span class="divider">/</span></li>
   	<li class="active">Listagem</li>
</ul>

<h2>Listagem de artigos</h2>

<form class="form-inline" action="${pageContext.request.contextPath}/artigo/busca" method="post">
	<input type="text" class="input-medium" placeholder="Título" name="titulo" value="${artigo.titulo}"/>
  	<div id="data_cadastro" class="input-append date">
    	<input data-format="dd/MM/yyyy" type="text" class="input-medium" placeholder="Data de cadastro"  name="dataCadastro" value="<joda:format value="${artigo.dataCadastro}" style="M-" />" />
    	<span class="add-on"><i data-time-icon="icon-time" data-date-icon="icon-calendar"></i></span>
  	</div>
  	<button type="submit" class="btn">Pesquisar</button>
</form>

<table class="table table-striped">
	<thead>
		<tr>
			<th>Título</th>
			<th>Data de Cadastro</th>
			<th>Ações</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${page.content}" var="artigo">
			<tr>
				<td>${artigo.titulo}</td>
				<td><joda:format value="${artigo.dataCadastro}" style="MM" /></td>
				<td class="actions">
					<a href="${pageContext.request.contextPath}/artigo/${artigo.id}" class="btn btn-small" title="Exibir"><i class="icon-file"></i></a>
					<a href="${pageContext.request.contextPath}/artigo/${artigo.id}/edita" class="btn btn-small" title="Editar"><i class="icon-pencil"></i></a>					
					<a href="" onclick="return confirmSubmitForm('Você tem certeza?', 'form_${artigo.id}');" class="btn btn-small" title="Excluir"><i class="icon-trash"></i></a>					
					<form id="form_${artigo.id}" class="hide" action="${pageContext.request.contextPath}/artigo/${artigo.id}" method="post">
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
		var url = '${pageContext.request.contextPath}/artigo/paginar/{{number}}';
		<c:if test="${not empty artigo.titulo or not empty artigo.dataCadastro}">
			url += buildParams('<c:out value="${artigo.titulo}" />', '<c:out value="${artigo.dataCadastro}" />');		
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
		
		$('#data_cadastro').datetimepicker({
	    	language: 'pt-BR'
	    });
	});
		
	function buildParams(titulo, data) {
		var params = '';
		var hasParams = false;

		if (titulo != '') {
			params += '?artigo.titulo=' + titulo;
			hasParams = true;
		} 
		
		if (data != '') {
			if (hasParams) {
				params += '&artigo.dataCadastro=' + data;
			} else {
				params += '?artigo.dataCadastro=' + data;
			}
		}
		
		return params;
	}
</script>

<br />

<a href="${pageContext.request.contextPath}/artigo/novo" class="btn"><i class="icon-plus"></i> Novo artigo</a> 
