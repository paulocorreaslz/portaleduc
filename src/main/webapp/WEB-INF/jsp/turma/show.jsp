<%@ page contentType="text/html; charset=UTF-8" %>

<title>Exibindo turma</title> 

<ul class="breadcrumb breadcrumb-wrapper">
       <li><a href="${pageContext.request.contextPath}/turmas">turmas</a> <span class="divider">/</span></li>
   	<li class="active">Exibir</li>
</ul>

    <div class="btn-group">
    <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
    Turma
    <span class="caret"></span>
    </a>
    <ul class="dropdown-menu">
       <li><a tabindex="-1" href="${pageContext.request.contextPath}/turma/${turma.id}/edita"">Editar</a></li>
       <li><a tabindex="-1" href="${pageContext.request.contextPath}/turma/relFichaTecnica/${turma.id}">Ficha técnica</a></li>      
       <li class="divider"></li>
       <li><a tabindex="-1" href="${pageContext.request.contextPath}/turmas">Listagem</a></li>
    </ul>
    </div>
 	<div class="btn-group">
    <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
    Alunos
    <span class="caret"></span>
    </a>
    <ul class="dropdown-menu">
    <li><a tabindex="-1" href="${pageContext.request.contextPath}/servidor/novoAluno/${turma.id}">Adicionar</a></li>
       <li><a tabindex="-1" href="${pageContext.request.contextPath}/aluno/turma/${turma.id}">Listar</a></li>
       <li><a tabindex="-1" href="${pageContext.request.contextPath}/aluno/relAlunosTurma/${turma.id}">Imprimir Alunos</a></li>
       <li><a tabindex="-1" href="${pageContext.request.contextPath}/aluno/relAlunosAprovadosTurma/${turma.id}">Imprimir Alunos Aprovados</a></li>
       <li><a tabindex="-1" href="${pageContext.request.contextPath}/aluno/relAlunosReprovadosTurma/${turma.id}">Imprimir Alunos Reprovados</a></li>
       
       <li><a tabindex="-1" href="${pageContext.request.contextPath}/turma/liberarCertificadosAlunos/${turma.id}">Liberar Certificados</a></li>
       <li><a tabindex="-1" href="${pageContext.request.contextPath}/turma/downloadCertificados/${turma.id}">Imprimir Certificados</a></li>
             
    </ul>
    </div>
    <div class="btn-group">
    <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
    Professores
    <span class="caret"></span>
    </a>
    <ul class="dropdown-menu">
       <li><a tabindex="-1" href="${pageContext.request.contextPath}/servidor/novoProfessor/${turma.id}">Adicionar</a></li>
       <li><a tabindex="-1" href="${pageContext.request.contextPath}/professor/turma/${turma.id}">Listar</a></li>
       <li><a tabindex="-1" href="${pageContext.request.contextPath}/professor/relProfessoresTurma/${turma.id}">Imprimir Relatório</a></li>
       <li><a tabindex="-1" href="${pageContext.request.contextPath}/turma/liberarDeclaracoesProfessores/${turma.id}">Liberar Declarações</a></li>      
    </ul>
    </div>
  

<h2>Exibindo turma</h2>

<p>Curso: <b>${turma.curso.nome}</b></p>
<p>Turma: <b>${turma.nome}</b></p>
<p>Carga horaria: <b>${turma.cargaHoraria}</b></p>
<p>Data de inicio: <b>${turma.dataInicialFormatada}</b></p>
<p>Data de termino: <b>${turma.dataFinalFormatada}</b></p>
<p>Conteudo: <b>${turma.conteudo}</b></p>

<br/>
<br/>
<h4>Estatísticas da turma</h4>
<p>Total de inscritos: <b>${turma.totalInscritos}</b></p>
<p>Alunos: <b>${turma.totalAlunos}</b></p>
<p>Professores: <b>${turma.totalProfessores}</b></p>
<p>Aprovados: <b>${turma.alunosAprovados}</b></p>
<p>Reprovados: <b>${turma.alunosReprovados}</b></p>
<p>Desistentes: <b>${turma.alunosDesistentes}</b></p>
<p>Evadidos: <b>${turma.alunosEvadidos}</b></p>
<p>Relação Tutor-Aluno: <b>${turma.relacaoTutorAluno}</b></p>
<p>Certificados Liberados: <b>${turma.certificadosLiberados}</b></p>
<p>Declarações Liberadas: <b>${turma.declaracoesLiberadas}</b></p>







<p>
	<br/>
	<!-- 
	<a href="${pageContext.request.contextPath}/turmas" class="btn"><i class="icon-list"></i> Listagem</a>
	<a href="${pageContext.request.contextPath}/turma/relFichaTecnica/${turma.id}" class="btn"><i class="icon-th"></i> Ficha Técnica</a>
	<a href="${pageContext.request.contextPath}/turma/liberarCertificadosAlunos/${turma.id}" class="btn"><i class="icon-th"></i> Liberar Certificados </a>
	<a href="${pageContext.request.contextPath}/turma/liberarDeclaracoesProfessores/${turma.id}" class="btn"><i class="icon-th"></i> Liberar Declarações </a>
	<a href="${pageContext.request.contextPath}/aluno/turma/${turma.id}" class="btn" title="Listar alunos"><i class="icon-list"></i> Alunos </a>
	<a href="${pageContext.request.contextPath}/professor/turma/${turma.id}" class="btn" title="Listar professores"><i class="icon-list"></i> Professores</a>
	-->				
</p>

