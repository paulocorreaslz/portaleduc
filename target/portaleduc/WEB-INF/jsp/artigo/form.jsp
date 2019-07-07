<%@ page contentType="text/html; charset=UTF-8" %>

<form action="${pageContext.request.contextPath}/artigo" method="post">
  
	<c:if test="${not empty artigo.id}">
		<input type="hidden" name="_method" value="put"/>
		<input type="hidden" name="artigo.id" value="${artigo.id}"/>
	</c:if>

	<label>Título:</label>
	<input type="text" class="input-xxlarge" name="artigo.titulo" value="${artigo.titulo}"/>

	<label>Conteúdo:</label>
	<textarea name="artigo.conteudo" rows="10" class="input-xxlarge">${artigo.conteudo}</textarea>

	<p>
		<br/>
	  	<button type="submit" class="btn"><i class="icon-ok"></i> Salvar</button>
	  	<a href="${pageContext.request.contextPath}/artigos" class="btn"><i class="icon-remove"></i> Cancelar</a>
  	</p>
</form>


