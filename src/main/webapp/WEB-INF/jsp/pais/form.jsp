<form action="${pageContext.request.contextPath}/pais" method="post">
  
	<c:if test="${not empty pais.id}">
		<input type="hidden" name="_method" value="put"/>
		<input type="hidden" name="pais.id" value="${pais.id}"/>
		<input type="hidden" name="pais.version" value="${pais.version}"/>
	</c:if>

	<label>Nome:</label>
	<input type="text" name="pais.nome" value="${pais.nome}"/>

	<label>Sigla:</label>
	<input type="text" name="pais.sigla" value="${pais.sigla}"/>

	<p>
		<br/>
	  	<button type="submit" class="btn"><i class="icon-ok"></i> Salvar</button>
	  	<a href="${pageContext.request.contextPath}/pais" class="btn"><i class="icon-remove"></i> Cancelar</a>
  	</p>
</form>


