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
		<a href="${pageContext.request.contextPath}/certificado/verCertificado/${certificado.id}" class="btn"><i class="icon-certificate"></i> Ver Certificado</a>
	</c:if>
	<c:if test="${not empty certificado.professor.id}">		
		<a href="${pageContext.request.contextPath}/certificado/verDeclaracao/${certificado.id}" class="btn"><i class="icon-certificate"></i> Ver Declaração</a>
	</c:if>
</p>
