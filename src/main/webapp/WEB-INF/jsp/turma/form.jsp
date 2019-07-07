<%@ page contentType="text/html; charset=UTF-8" %>

<form action="${pageContext.request.contextPath}/turma" method="post">
	<!-- tem que ter a declaracao do id do curso pra poder instaciar direito o objeto -->
	<input type="hidden" name="turma.curso.id" value="${turma.curso.id}"/>
  
  	<!-- se a turma tiver ID, vai ser PUT (chama o método update) e nao POST(metodo create) -->
	<c:if test="${not empty turma.id}">
		<input type="hidden" name="_method" value="put"/>
		<input type="hidden" name="turma.id" value="${turma.id}"/>
	</c:if>

    <label>Nome do Curso:</label>
	<p>${turma.curso.nome}</p>

	<label>Nome:</label>
	<input type="text" class="input-xxlarge" name="turma.nome" value="${turma.nome}"/>

	<label>Carga horária:</label>
	<input type="text" class="input-xxlarge" name="turma.cargaHoraria" value="${turma.cargaHoraria}"/>

	<label>Público:</label>
	<textarea name="turma.publico" rows="10" class="input-xxlarge">${turma.publico}</textarea>

	<label>Tutoria:</label>
	<textarea name="turma.tutoria" rows="10" class="input-xxlarge">${turma.tutoria}</textarea>

	<label>Tempo de conclusão:</label>
	<input type="text" class="input-small" name="turma.tempoConclusao" value="${turma.tempoConclusao}"/>

	<label>Estratégias de aprendizagem:</label>
	<textarea name="turma.estrategias" rows="10" class="input-xxlarge">${turma.estrategias}</textarea>

	<label>Vagas Servidores de Cartório:</label>
	<input type="text" class="input-small" name="turma.vagasCartorio" value="${turma.vagasCartorio}"/>

	<label>Vagas Servidores da Secretaria:</label>
	<input type="text" class="input-small" name="turma.vagasSecretaria" value="${turma.vagasSecretaria}"/>

	<label>Valor da hora de tutoria :</label>
	<input type="text" class="input-small" name="turma.horaTutoria" value="${turma.horaTutoria}"/>

	<label>Métodos de interação e comunicação:</label>
	<textarea name="turma.metodoInteracao" rows="10" class="input-xxlarge">${turma.metodoInteracao}</textarea>

	<label>Métodos de avaliação:</label>
	<textarea name="turma.metodoAvaliacao" rows="10" class="input-xxlarge">${turma.metodoAvaliacao}</textarea>

	<label>Observações:</label>
	<textarea name="turma.observacoes" rows="10" class="input-xxlarge">${turma.observacoes}</textarea>

	<label>Conteúdo programático:</label>
	<textarea name="turma.conteudo" rows="10" class="input-xxlarge">${turma.conteudo}</textarea>

	<label>Data inicio: </label>
	<vrb:datetimepicker name="turma.dataInicial" value="${turma.dataInicial}" placeholder="Data de Inicio" />				
			
	<label>Data final: </label>
	<vrb:datetimepicker name="turma.dataFinal" value="${turma.dataFinal}" placeholder="Data de Termino" />				
	<p>
		<br/>
	  	<button type="submit" class="btn"><i class="icon-ok"></i> Salvar</button>
	  	<a href="${pageContext.request.contextPath}/turma" class="btn"><i class="icon-remove"></i> Cancelar</a>
  	</p>
</form>


