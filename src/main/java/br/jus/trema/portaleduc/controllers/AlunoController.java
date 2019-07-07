package br.jus.trema.portaleduc.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.time.DateTime;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.interceptor.download.Download;
import br.jus.trema.portaleduc.enums.AppRole;
import br.jus.trema.portaleduc.enums.Eleitoral;
import br.jus.trema.portaleduc.enums.SituacaoAluno;
import br.jus.trema.portaleduc.enums.TipoCurso;
import br.jus.trema.portaleduc.enums.ValidoAQ;
import br.jus.trema.portaleduc.enums.VersaoMoodle;
import br.jus.trema.portaleduc.models.Aluno;
import br.jus.trema.portaleduc.models.Servidor;
import br.jus.trema.portaleduc.models.Turma;
import br.jus.trema.portaleduc.reports.AlunoReport;
import br.jus.trema.portaleduc.repositories.AlunoRepo;
import br.jus.trema.portaleduc.repositories.ServidorRepo;
import br.jus.trema.portaleduc.repositories.TurmaRepo;
import br.jus.trema.vraptorbridge.authentication.AuthenticationControl;
import br.jus.trema.vraptorbridge.authorization.annotations.Permission;
import br.jus.trema.vraptorbridge.messages.Messenger;
import br.jus.trema.vraptorbridge.pagination.Page;
import br.jus.trema.vraptorbridge.pagination.Paginator;
import br.jus.trema.vraptorbridge.reports.Reporter;
import br.jus.trema.vraptorbridge.util.Params;

/**
 * 
 * @author Paulo Correa <paulo.correa@tre-ma.gov.br>
 *
 */
@Resource
@Permission(AppRole.ADMIN)
public class AlunoController {
	
	private final Result result;
	private final Validator validator;
	private final ServidorRepo servidorRepo;
	private final TurmaRepo turmaRepo;
	private final AlunoRepo alunoRepo;
	private final Messenger messenger;
	private final Reporter reporter;
	private final AuthenticationControl authenticationControl;
	private final Paginator<Aluno> paginator;
	private final Paginator<Turma> turmaPaginator;
	
	
	public AlunoController(Result result, AuthenticationControl authenticationControl, Reporter reporter,Validator validator,TurmaRepo turmaRepo, ServidorRepo servidorRepo, AlunoRepo alunoRepo,  Messenger messenger) {
		this.result = result;
		this.reporter = reporter;
		this.validator = validator;
		this.servidorRepo = servidorRepo;
		this.turmaRepo = turmaRepo;		
		this.authenticationControl = authenticationControl;
		this.alunoRepo = alunoRepo;
		this.messenger = messenger;
		this.paginator = new Paginator<Aluno>(alunoRepo);
		this.turmaPaginator = new Paginator<Turma>(turmaRepo);
	}
	
	@Get("/aluno")
	public Page<Aluno> list(Aluno aluno, int pagina) {
		result.include("aluno", aluno);
		return paginator.paginate(aluno, pagina, "nome");
	}
		
	@Get("/aluno/novo/{servidorid}/{turmaid}")
	public Aluno newAluno(Long servidorid, Long turmaid) {
		buildLists();
		Turma turma = turmaRepo.find(turmaid);
		Aluno aluno = new Aluno(turma);
		if (!(servidorid == null)) {
			Servidor servidor = servidorRepo.find(servidorid);		
			if (servidor.getLotacao() == null) {
				aluno.setLotacao(servidor.getSituacao());
			}else {
				aluno.setLotacao(servidor.getLotacao());
			}		
			aluno.setMatricula(servidor.getMatricula());
			aluno.setNome(servidor.getNome());
			aluno.setSituacao(SituacaoAluno.A);	
			aluno.setTurma(turma);
			result.include("servidor", servidor);
		} else {
			aluno.setMatricula("000000");
			aluno.setLotacao("ZE-000");
			aluno.setNome("NOME DO ALUNO");
			aluno.setSituacao(SituacaoAluno.A);
		}
		result.include("turma", turma);
		return aluno;
	}
	
	@Get("/aluno/novoAlunoExterno/{turmaid}")
	public Aluno newAlunoExterno(Long turmaid) {
		buildLists();
		
		Turma turma = turmaRepo.find(turmaid);
		Aluno aluno = new Aluno(turma);
		aluno.setMatricula("000000");
		aluno.setLotacao("ZE-000");
		aluno.setNome("NOME DO ALUNO");
		aluno.setSituacao(SituacaoAluno.A);	
		result.include("turma", turma);
		return aluno;
	}
	
	@Get("/aluno/paginar/{pagina}")
	public void paginating(Aluno aluno, int pagina, Long turmaid) {
		if (turmaid != null) {
			Turma turma = turmaRepo.find(turmaid);
			aluno.setTurma(turma);
			result.include("turma", turma);	
		}			
		result.redirectTo(this).list(aluno, pagina);
	}
	
	@Get("/aluno/{aluno.id}")
	public Aluno show(Aluno aluno) {
		Turma turma = aluno.getTurma();
		result.include("turma", turma);
		return alunoRepo.find(aluno.getId());
	}
		
	@Post("/aluno")
	public void create(Aluno aluno, Long servidorid, Long turmaid) {
		buildLists();
		aluno.setDataCadastro(DateTime.now());		
		validator.validate(aluno);		
		aluno.setTurma(turmaRepo.find(turmaid));
		validator.onErrorUsePageOf(this).newAluno(servidorid, turmaid);
		alunoRepo.save(aluno);
		messenger.addSuccess("Aluno salvo com sucesso!");
		result.redirectTo(this).show(aluno);
	}
	
	@Get("/aluno/turma/{turmaid}")
	public void alunoTurma(Long turmaid) {
		Aluno aluno = new Aluno();
		if (turmaid != null) {
			aluno.setTurma(turmaRepo.find(turmaid));			
		}			
		result.include("turma", turmaRepo.find(turmaid));
		result.redirectTo(this).list(aluno, 1);
	}
	
	@Post("/aluno/busca")
	public void find(String matricula, String nome, Long turmaid) {
		Aluno aluno = new Aluno();
		if (matricula != null) aluno.setMatricula(matricula);
		if (nome != null) aluno.setNome(nome); 
		if (turmaid != null) {
			aluno.setTurma(turmaRepo.find(turmaid));
			result.include("turma", turmaRepo.find(turmaid));
		}
		result.redirectTo(this).list(aluno, 1);
	}
		
	@Put("/aluno")
	public void update(Aluno aluno) {
		buildLists();		
		Aluno alunoNovo = alunoRepo.find(aluno.getId());
		alunoNovo.setDataCadastro(alunoNovo.getDataCadastro());	
		alunoNovo.setLotacao(aluno.getLotacao());
		alunoNovo.setSituacao(aluno.getSituacao());
		alunoNovo.setJustificativa(aluno.getJustificativa());
		validator.validate(alunoNovo);
		validator.onErrorUsePageOf(this).edit(alunoNovo);
		alunoRepo.save(alunoNovo);
		messenger.addSuccess("As informações do aluno foram alteradas com sucesso!");
		result.forwardTo(this).show(alunoNovo);
	}
	
	@Get("/aluno/{aluno.id}/edita")
	public Aluno edit(Aluno aluno) {
		buildLists();
		return alunoRepo.find(aluno.getId());
	}
	

	@Delete("/aluno/{aluno.id}")
	public void delete(Aluno aluno) {
		Long turmaid = (Long) alunoRepo.find(aluno.getId()).getTurma().getId();		
		alunoRepo.delete(alunoRepo.find(aluno.getId()));		
		messenger.addSuccess("O aluno foi excluído com sucesso!");
		result.redirectTo(this).alunoTurma(turmaid);  
	}

	@Path("/aluno/relAlunosTurma/{turmaid}") 
	public Download preparaFichaTecnica(Long turmaid){				
		
		List<Aluno> alunos = alunoRepo.findByTurma(turmaRepo.find(turmaid));
		
		for (Aluno aluno : alunos) {
			//turma.setDataFinalT(turma.getDataFinal().toDate());
			if (aluno.getSituacao().equals(SituacaoAluno.A)) aluno.setSituacaoT("Aprovado");
			if (aluno.getSituacao().equals(SituacaoAluno.R)) aluno.setSituacaoT("Reprovado");
			if (aluno.getSituacao().equals(SituacaoAluno.D)) aluno.setSituacaoT("Desistiu");
			if (aluno.getSituacao().equals(SituacaoAluno.E)) aluno.setSituacaoT("Evadiu");
			
			aluno.getTurma().setDataInicialT(aluno.getTurma().getDataInicial().toDate());
			aluno.getTurma().setDataFinalT(aluno.getTurma().getDataFinal().toDate());
			
		}		
		AlunoReport report = new AlunoReport(alunos, "relAlunosTurma.jasper");		
		return reporter.pdfReport(report);
	}
	
	@Path("/aluno/relAlunosAprovadosTurma/{turmaid}") 
	public Download alunosAprovadosRel(Long turmaid){				
		
		List<Aluno> alunos = alunoRepo.findByTurma(turmaRepo.find(turmaid));
		List<Aluno> aprovados = new ArrayList<Aluno>();
				
		for (Aluno aluno : alunos) {				
			if (aluno.getSituacao().equals(SituacaoAluno.A)) aluno.setSituacaoT("Aprovado");
			aluno.getTurma().setDataInicialT(aluno.getTurma().getDataInicial().toDate());
			aluno.getTurma().setDataFinalT(aluno.getTurma().getDataFinal().toDate());
			if (aluno.getSituacao().equals(SituacaoAluno.A)) aprovados.add(aluno);
		}		
		AlunoReport report = new AlunoReport(aprovados, "relAlunosTurma.jasper");		
		return reporter.pdfReport(report);
	}
	
	@Path("/aluno/relAlunosReprovadosTurma/{turmaid}") 
	public Download alunosReprovadosRel(Long turmaid){				
		
		List<Aluno> alunos = alunoRepo.findByTurma(turmaRepo.find(turmaid));
		List<Aluno> reprovados = new ArrayList<Aluno>();
				
		for (Aluno aluno : alunos) {				
			if (aluno.getSituacao().equals(SituacaoAluno.R)) aluno.setSituacaoT("Reprovado");
			if (aluno.getSituacao().equals(SituacaoAluno.E)) aluno.setSituacaoT("Evadiu");
			if (aluno.getSituacao().equals(SituacaoAluno.D)) aluno.setSituacaoT("Desistiu");
			aluno.getTurma().setDataInicialT(aluno.getTurma().getDataInicial().toDate());
			aluno.getTurma().setDataFinalT(aluno.getTurma().getDataFinal().toDate());
			if (!aluno.getSituacao().equals(SituacaoAluno.A)) reprovados.add(aluno);
		}		
		AlunoReport report = new AlunoReport(reprovados, "relAlunosTurma.jasper");		
		return reporter.pdfReport(report);
	}
	
	@Permission(AppRole.TRE)	
	@Get("/aluno/meuscursos")
	public Page<Turma> minhasTurmas(int pagina) {
		Aluno aluno = new Aluno();
		aluno.setMatricula(authenticationControl.getLoggedUser().getId());
		List<Aluno> alunos = new ArrayList<Aluno>();
		alunos.add(aluno);
		
		Turma turma = new Turma();
		turma.setAlunos(alunos);
				
		result.include("aluno", aluno);		
		return turmaPaginator.paginate(turma, pagina, "dataInicial", Params.DESC_ORDER);		
	}
	
	
	@Get("/aluno/verCursos")
	public void verCursos() {
		messenger.addInformation("Digite a matricula do aluno para localizar os cursos que o aluno participou!");		
	}
	
	
	@Post("/aluno/localizarCursos")
	public Page<Turma> localizarTurmas(int pagina, String matricula) {
		List<Aluno> alunosMat = alunoRepo.findByMatricula(matricula);
		Aluno aluno = new Aluno();
		if (alunosMat.size() > 0) {
			aluno = alunosMat.get(0);
		} else {
			aluno.setMatricula(matricula.trim());
		}
		List<Aluno> alunos = new ArrayList<Aluno>();
		alunos.add(aluno);
		
		Turma turma = new Turma();
		turma.setAlunos(alunos);
				
		result.include("aluno", aluno);		
		return turmaPaginator.paginate(turma, pagina, "dataInicial", Params.DESC_ORDER);		
	}
	
	
	@Get("/aluno/paginateTurmas2/{pagina}")
	public Page<Turma> paginateTurmas2(int pagina) {
		return localizarTurmas(pagina, null);
	}
	
	@Permission(AppRole.TRE)
	@Get("/aluno/paginateTurmas/{pagina}")
	public Page<Turma> paginateTurmas(int pagina) {		
		return minhasTurmas(pagina);
	}
	
	@Permission(AppRole.TRE)
	@Get("/aluno/meuscertificados")
	public Page<Aluno> meusCertificados(int pagina) {
		Aluno aluno = new Aluno();		
		aluno.setMatricula(authenticationControl.getLoggedUser().getId());	
		return paginator.paginate(aluno, pagina);
		//result.redirectTo(this).meusCertificados(aluno, 1);
	}
	
	@Permission(AppRole.TRE)
	@Get("/aluno/paginarAlunos/{pagina}")
	public void paginatingCertificados(int pagina) {
		result.redirectTo(this).meusCertificados(pagina);
	}

	@Permission(AppRole.TRE)
	@Get("/aluno/turmasAbertas")
	public List<Turma> turmasAbertas(int pagina) {
		Turma turma = new Turma();		
		return turmaRepo.listaTurmasAbertasUltimaSemana();		
	}
	@Permission(AppRole.TRE)
	@Get("/aluno/paginarTurmas/{pagina}")
	public void paginatingTurmas(int pagina) {
		result.redirectTo(this).turmasAbertas(pagina);
	}
	
	private void buildLists() {
		result.include("situacaoAlunoList", Arrays.asList(SituacaoAluno.values()));		
	}
}