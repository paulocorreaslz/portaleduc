<%@ page contentType="text/html; charset=UTF-8" %>

<form action="${pageContext.request.contextPath}/aluno" method="post">
  
	<!-- tem que ter a declaracao do id do curso pra poder instaciar direito o objeto -->
	<input type="hidden" name="professor.id" value="${professor.id}"/>
	<input type="hidden" name="servidorid" value="${servidor.id}"/>  
  	<input type="hidden" name="turmaid" value="${turma.id}"/>  
  
  	<!-- se a turma tiver ID, vai ser PUT (chama o método update) e nao POST(metodo create) -->
	<c:if test="${not empty aluno.id}">
		<input type="hidden" name="_method" value="put"/>
		<input type="hidden" name="aluno.id" value="${aluno.id}"/>
	</c:if>

	<label>Nome da Turma:</label>
	<p>${turma.nome}</p>

	<label>Matricula:</label>
	<input type="text" class="input-xxlarge" name="aluno.matricula" value="${aluno.matricula}"/>


	<label>Nome:</label>
	<input type="text" class="input-xxlarge" name="aluno.nome" value="${aluno.nome}"/>


	<label>Lotação:</label>
	<input type="text" class="input-xxlarge" name="aluno.lotacao" value="${aluno.lotacao}"/>
	
	<label>Situação:</label>
	<vrb:select items="${situacaoAlunoList}" name="aluno.situacao" selected="${aluno.situacao.name}" optionContent="value" optionValue="name"  />
	
	<label>Justificativa:</label>
	<textarea name="aluno.justificativa" rows="10" class="input-xxlarge">${aluno.justificativa}</textarea>
	
	<p>
		<br/>
	  	<button type="submit" class="btn"><i class="icon-ok"></i> Salvar</button>
	  	<a href="${pageContext.request.contextPath}/aluno" class="btn"><i class="icon-remove"></i> Cancelar</a>
  	</p>
</form>


