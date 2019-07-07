<%@ page contentType="text/html; charset=UTF-8" %>

<title>Página inicial</title>

<div class="hero-unit">
 	<h1>
 		<img src="${pageContext.request.contextPath}/img/logo-login.png" class="img-polaroid logo-h1">
 		Oi, bem vindo ao VRaptorBlog!
		</h1>
</div>

<div class="row">
	<c:forEach items="${page.content}" var="artigo">
  		<div class="span4">
    		<h2>${artigo.titulo}</h2>
   			<small>Postado em <joda:format value="${artigo.dataCadastro}" style="MM" /></small>
    		<p>${artigo.preview}</p>
    		<p><a class="btn" href="${pageContext.request.contextPath}/blog/${artigo.id}">Continue lendo &raquo;</a></p>
  		</div>
	</c:forEach>
</div>

<hr>

<div id="pagination-totals" class="left">
	<span class="label label-info">${page.totalElements} artigos cadastrados</span>
</div>

<div id="pagination-links" class="pagination-right">
	<span class="label label-important">Não há itens a serem exibidos</span>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		var url = '${pageContext.request.contextPath}/blog/paginar/{{number}}';
	
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
</script>
