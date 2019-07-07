<%@ page contentType="text/html; charset=UTF-8" %>

<form action="${pageContext.request.contextPath}/curso" method="post">
  
	<c:if test="${not empty curso.id}">
		<input type="hidden" name="_method" value="put"/>
		<input type="hidden" name="curso.id" value="${curso.id}"/>
	</c:if>

	<label>Nome:</label>
	<input type="text" class="input-xxlarge" name="curso.nome" value="${curso.nome}"/>

	<label>Tipo:</label>
	<vrb:select items="${tipoCursoList}" name="curso.tipoCurso" selected="${curso.tipoCurso.name}" optionContent="value" optionValue="name" />

	<label>Eleitoral:</label>
	<vrb:select items="${eleitoralList}" name="curso.eleitoral" selected="${curso.eleitoral.name}" optionContent="value" optionValue="name"  />

	<label>Adicional de Qualificação:</label>
	<vrb:select items="${validoAQList}" name="curso.validoAQ" selected="${curso.validoAQ.name}" optionContent="value" optionValue="name"  />

	<label>Versão:</label>	
	<vrb:select items="${versaoMoodleList}" name="curso.versaoMoodle" selected="${curso.versaoMoodle.name}" optionContent="value" optionValue="name" />

	<label>Objetivo do curso:</label>
	<textarea name="curso.objetivo" rows="10" class="input-xxlarge">${curso.objetivo}</textarea>

	<label>Justificativa do curso:</label>
	<textarea name="curso.justificativa" rows="10" class="input-xxlarge">${curso.justificativa}</textarea>

	<label>Data: </label>
		<vrb:datetimepicker name="curso.dataCadastro" value="${curso.dataCadastro}" placeholder="Data de Cadastro" />				
	<p>
		<br/>
	  	<button type="submit" class="btn"><i class="icon-ok"></i> Salvar</button>
	  	<a href="${pageContext.request.contextPath}/cursos" class="btn"><i class="icon-remove"></i> Cancelar</a>
  	</p>
</form>


