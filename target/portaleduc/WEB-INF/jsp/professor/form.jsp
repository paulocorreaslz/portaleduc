<%@ page contentType="text/html; charset=UTF-8" %>

<form action="${pageContext.request.contextPath}/professor" method="post">
	<!-- tem que ter a declaracao do id do curso pra poder instaciar direito o objeto -->
	<input type="hidden" name="professor.id" value="${professor.id}"/>
	<input type="hidden" name="servidorid" value="${servidor.id}"/>  
  	<input type="hidden" name="turmaid" value="${turma.id}"/>  
  
  	<!-- se a turma tiver ID, vai ser PUT (chama o método update) e nao POST(metodo create) -->
	<c:if test="${not empty professor.id}">
		<input type="hidden" name="_method" value="put"/>
		<input type="hidden" name="professor.id" value="${professor.id}"/>
	</c:if>

    <label>Nome da Turma:</label>
	<p>${turma.nome}</p>

	<label>Matricula:</label>
	<input type="text" class="input-xxlarge" name="professor.matricula" value="${professor.matricula}"/>


	<label>Nome:</label>
	<input type="text" class="input-xxlarge" name="professor.nome" value="${professor.nome}"/>


	<label>Lotação:</label>
	<input type="text" class="input-xxlarge" name="professor.lotacao" value="${professor.lotacao}"/>
	
	<label>Situação:</label>
	
	<input type="text" class="input-xxlarge" name="professor.situacao" value="${professor.situacao}"/>
	
	<label>Tipo de Professor:</label>
	<vrb:select items="${TipoProfessorList}" name="professor.tipoProfessor" selected="${professor.tipoProfessor.name}" optionContent="value" optionValue="name"  />
		
	<p>
		<br/>
	  	<button type="submit" class="btn"><i class="icon-ok"></i> Salvar</button>
	  	<a href="${pageContext.request.contextPath}/professor" class="btn"><i class="icon-remove"></i> Cancelar</a>
  	</p>
</form>


