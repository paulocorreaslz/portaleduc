<%@ page contentType="text/html; charset=UTF-8" %>

<form action="${pageContext.request.contextPath}/estado" method="post">
  
	<c:if test="${not empty estado.id}">
		<input type="hidden" name="_method" value="put"/>
		<input type="hidden" name="estado.id" value="${estado.id}"/>
		<input type="hidden" name="estado.version" value="${estado.version}"/>
	</c:if>

	<label>Nome:</label>
	<input type="text" name="estado.nome" value="${estado.nome}"/>

	<label>Sigla:</label>
	<input type="text" name="estado.sigla" value="${estado.sigla}"/>
	
	<label>País:</label> 	
	<w:select name="estado.pais.id" items="${paisList}" value="id" var="pais" selected="${estado.pais.id}" addEmpty="true" >  
  		${pais.nome}  
	</w:select>  

	<p>
		<br/>
	  	<button type="submit" class="btn"><i class="icon-ok"></i> Salvar</button>
	  	<a href="${pageContext.request.contextPath}/estado" class="btn"><i class="icon-remove"></i> Cancelar</a>
  	</p>
</form>


