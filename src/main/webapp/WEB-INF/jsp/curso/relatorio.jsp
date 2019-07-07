<%@ page contentType="text/html; charset=UTF-8" %>

<title>Gerar Relatório de Cursos</title>

<ul class="breadcrumb breadcrumb-wrapper">
    <li><a href="${pageContext.request.contextPath}/cursos">Cursos</a> <span class="divider">/</span></li>
   	<li class="active">Listagem</li>
</ul>

<h2>Relatório de cursos</h2>

<form class="form-inline" action="${pageContext.request.contextPath}/curso/relatorioGeral" method="post">
	
	<label>Nome do curso: </label>	
	<p><input type="text" class="input-xxlarge" placeholder="nome" name="nome" value="${curso.nome}" width="200"></p>
	
	<label>Data inicio: </label>
	<p><vrb:datetimepicker name="dataCadastro" placeholder="Data de Inicio"  value="${curso.dataCadastro}"/></p>				
		
	<p><button type="submit" class="btn"><i class="icon-print"></i> Gerar Relatorio</button></p>
	  
</form>

