<%@ page contentType="text/html; charset=UTF-8" %>



<title>Exibindo curso</title>

<ul class="breadcrumb breadcrumb-wrapper">
       <li><a href="${pageContext.request.contextPath}/cursos">cursos</a> <span class="divider">/</span></li>
   	<li class="active">Exibir</li>
</ul>

<div class="btn-group">
    <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
    Curso
    <span class="caret"></span>
    </a>
    <ul class="dropdown-menu">
       <li><a tabindex="-1" href="${pageContext.request.contextPath}/curso/${curso.id}/edita"">Editar</a></li>
       <li><a tabindex="-1" href="${pageContext.request.contextPath}/curso/relCursosGeral/${curso.id}">Relátorio de cursos</a></li>      
       <li class="divider"></li>
       <li><a tabindex="-1" href="${pageContext.request.contextPath}/cursos">Listagem</a></li>
    </ul>
    </div>


<div class="btn-group">
    <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
    Turmas
    <span class="caret"></span>
    </a>
    <ul class="dropdown-menu">
    
    <li><a tabindex="-1" href="${pageContext.request.contextPath}/turma/novo/${curso.id}">Nova turma</a></li>     
       <li><a tabindex="-1" href="${pageContext.request.contextPath}/curso/relCursosGeral/${curso.id}">Relátorio de turmas</a></li>
       <li class="divider"></li>      
       <li><a tabindex="-1" href="${pageContext.request.contextPath}/turmas/Curso/${curso.id}">Listagem</a></li>
    </ul>
    </div>
  

<h2>Exibindo Curso: </h2>


<p>Nome: ${curso.nome}</p>

<p>Tipo de curso: ${curso.tipoCurso}</p>

<p>Curso eleitoral: ${curso.eleitoral}</p>

<p>Versão do moodle: ${curso.versaoMoodle}</p>

<p>Data de cadastro: ${curso.dataCadastro}</p>

<h4>Estatísticas do Curso</h4>
<p>Quantidade de turmas: <b>${curso.totalTurmas}</b></p>

<p>
	<br/>
	<a href="${pageContext.request.contextPath}/curso/${curso.id}/edita" class="btn"><i class="icon-pencil"></i> Editar</a>
	<a href="${pageContext.request.contextPath}/cursos" class="btn"><i class="icon-list"></i> Listagem</a>
</p>
