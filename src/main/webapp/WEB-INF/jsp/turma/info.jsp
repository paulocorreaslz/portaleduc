<%@ page contentType="text/html; charset=UTF-8" %>

<title>Exibindo turma</title> 

<ul class="breadcrumb breadcrumb-wrapper">
       <li><a href="${pageContext.request.contextPath}/turmas">turmas</a> <span class="divider">/</span></li>
   	<li class="active">Exibir</li>
</ul>

  
<h2>Exibindo turma</h2>

<p>Curso: <b>${turma.curso.nome}</b></p>
<p>Turma: <b>${turma.nome}</b></p>
<p>Carga horaria: <b>${turma.cargaHoraria}</b></p>
<p>Data de inicio: <b>${turma.getDataInicialFormatada()}</b></p>
<p>Data de termino: <b>${turma.getDataFinalFormatada()}</b></p>
<p>Conteudo: <b>${turma.conteudo}</b></p>
