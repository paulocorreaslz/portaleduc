<%@ page contentType="text/html; charset=UTF-8" %>

<title>Exibindo certificado</title>

<ul class="breadcrumb breadcrumb-wrapper">
       <li><a href="${pageContext.request.contextPath}/certificado">certificado</a> <span class="divider">/</span></li>
   	<li class="active">Exibir</li>
</ul>

<h2>Certificado</h2>

<p>Curso: ${turma.curso.nome}</p>

<p>Turma: ${turma.nome}</p>

<p>Código: ${certificado.codigo}</p>

<p>
	<br/>
	<c:if test="${not empty certificado.aluno.id}">	
	<div class="center">
	<img src="<c:url value="/certificado/${certificado.id}/imagemCertificado"/>" class="img-polaroid" alt="Aguarde..." />
	</div>		
		<a href="${pageContext.request.contextPath}/certificado/verCertificado/${certificado.id}" class="btn"><i class="icon-certificate"></i> Baixar Certificado</a>
	</c:if>
	<c:if test="${not empty certificado.professor.id}">
	<div class="center">
		<img src="<c:url value="/certificado/${certificado.id}/imagemDeclaracao"/>" class="img-polaroid" alt="Aguarde..." />
	</div>			
		<a href="${pageContext.request.contextPath}/certificado/verDeclaracao/${certificado.id}" class="btn"><i class="icon-certificate"></i> Baixar Declaração</a>
	</c:if>
</p>
