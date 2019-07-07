package br.jus.trema.portaleduc.controllers;

import java.util.List;

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
import br.jus.trema.portaleduc.enums.ValidoAQ;
import br.jus.trema.portaleduc.models.Aluno;
import br.jus.trema.portaleduc.models.Curso;
import br.jus.trema.portaleduc.models.Turma;
import br.jus.trema.portaleduc.reports.TurmaReport;
import br.jus.trema.portaleduc.repositories.AlunoRepo;
import br.jus.trema.portaleduc.repositories.CertificadoRepo;
import br.jus.trema.portaleduc.repositories.CursoRepo;
import br.jus.trema.portaleduc.repositories.TurmaRepo;
import br.jus.trema.vraptorbridge.authentication.AuthenticationControl;
import br.jus.trema.vraptorbridge.authorization.annotations.Permission;
import br.jus.trema.vraptorbridge.messages.Messenger;
import br.jus.trema.vraptorbridge.pagination.Page;
import br.jus.trema.vraptorbridge.pagination.Paginator;
import br.jus.trema.vraptorbridge.reports.Reporter;
import br.jus.trema.vraptorbridge.repositories.UserRepo;
import br.jus.trema.vraptorbridge.util.Params;

/**
 * 
 * @author Paulo Correa <paulo.correa@tre-ma.gov.br>
 *
 */
@Resource
@Permission(AppRole.ADMIN)
public class TurmaController {

	private final Result result;
	private final Validator validator;
	private final CursoRepo cursoRepo;
	private final TurmaRepo turmaRepo;
	private final UserRepo userRepo;
	private final AlunoRepo alunoRepo;
	private final CertificadoRepo certificadoRepo;
	private final Messenger messenger;
	private final Reporter reporter;
	private final Paginator<Turma> paginator;
	private final AuthenticationControl authenticationControl;

	public TurmaController(Result result,AuthenticationControl authenticationControl, AlunoRepo alunoRepo, UserRepo userRepo, Reporter reporter, CertificadoRepo certificadoRepo, Validator validator, CursoRepo cursoRepo, TurmaRepo turmaRepo,  Messenger messenger) {
		this.result = result;
		this.validator = validator;
		this.cursoRepo = cursoRepo;
		this.userRepo = userRepo;
		this.alunoRepo =alunoRepo;
		this.certificadoRepo = certificadoRepo;	
		this.turmaRepo = turmaRepo;
		this.reporter = reporter;
		this.messenger = messenger;
		this.authenticationControl = authenticationControl;
		this.paginator = new Paginator<Turma>(turmaRepo);
	}

	@Get("/turmas")
	public Page<Turma> list(Turma turma, int pagina) {
		result.include("turma", turma);
		return paginator.paginate(turma, pagina, "dataInicial", Params.DESC_ORDER);
	}
	
	@Get("/turma/novo/{cursoid}")
	public Turma newTurma(Long cursoid) {	
		Curso curso = cursoRepo.find(cursoid);
		return new Turma(curso);
	}

	@Get("/turma/paginar/{pagina}")
	public void paginating(Turma turma, int pagina,Long cursoId) {
		result.include("curso", cursoRepo.find(cursoId));
		result.redirectTo(this).list(turma, pagina);
	}


	@Get("/turma/{turma.id}")
	public Turma show(Turma turma) {
		return turmaRepo.find(turma.getId());
	}
	
	@Permission(AppRole.TRE)
	@Get("/turma/info/{turma.id}")
	public Turma info(Turma turma) {
		return turmaRepo.find(turma.getId());
	}
	
	@Permission(AppRole.TRE)
	@Get("/turma/info/{turma.id}")
	public Turma inscricao(Turma turma) {
		return turmaRepo.find(turma.getId());
	}
	
	@Get("/turma/{turma.id}/edita")
	public Turma edit(Turma turma) {		
		return turmaRepo.find(turma.getId());
	}

	@Delete("/turma/{turma.id}")
	public void delete(Turma turma) {
		turmaRepo.delete(turmaRepo.find(turma.getId()));
		messenger.addSuccess("A turma foi excluída com sucesso!");
		result.forwardTo(this).list(null, 1);  
	}

	@Post("/turma")
	public void create(Turma turma) {			
		validator.validate(turma);
		validator.onErrorRedirectTo(this).newTurma(turma.getCurso().getId());
		turmaRepo.save(turma);
		messenger.addSuccess("Turma salva com sucesso!");
		//result.include("curso", turma.getCurso());
		result.forwardTo(this).show(turma);
	}
	
	@Put("/turma")
	public void update(Turma turma) {
		validator.validate(turma);
		validator.onErrorUsePageOf(this).edit(turma);
		Turma novaTurma = turmaRepo.find(turma.getId());
		
		novaTurma.setNome(turma.getNome());		
		novaTurma.setConteudo(turma.getConteudo());
		novaTurma.setPublico(turma.getPublico());
		novaTurma.setEstrategias(turma.getEstrategias());
		novaTurma.setHoraTutoria(turma.getHoraTutoria());
		novaTurma.setMetodoAvaliacao(turma.getMetodoAvaliacao());
		novaTurma.setMetodoInteracao(turma.getMetodoInteracao());
		novaTurma.setObservacoes(turma.getObservacoes());
		novaTurma.setTempoConclusao(turma.getTempoConclusao());
		novaTurma.setTutoria(turma.getTutoria());
		novaTurma.setCargaHoraria(turma.getCargaHoraria());
		novaTurma.setVagasCartorio(turma.getVagasCartorio());
		novaTurma.setVagasSecretaria(turma.getVagasSecretaria());
		novaTurma.setHoraTutoria(turma.getHoraTutoria());
		novaTurma.setDataInicial(turma.getDataInicial());
		novaTurma.setDataFinal(turma.getDataFinal());
		turmaRepo.save(novaTurma);
		messenger.addSuccess("As informações da turma foram alteradas com sucesso!");
		result.forwardTo(this).show(novaTurma);
	}

	@Get("/turmas/Curso/{cursoid}")
	public void turmasCurso(Long cursoid) {
		Turma turmax = new Turma();	
		if (cursoid != null) {
			Curso curso = cursoRepo.find(cursoid);
			turmax.setCurso(curso);
			result.include("curso", curso);
		}					
		result.redirectTo(this).list(turmax, 1);
	}


	@Post("/turma/busca")
	public void find(String nome) {
		Turma turma = new Turma();
		if (nome != null && !nome.isEmpty()) turma.setNome(nome);
			result.redirectTo(this).list(turma, 1);
	}
	
	@Path("/turma/relTurmasGeral") 
	public Download preparaRelatorio(Long cursoid){				
		
		List<Turma> turmas = turmaRepo.findAll();
		
		for (Turma turma : turmas) {
			turma.setDataFinalT(turma.getDataFinal().toDate());
			turma.setDataInicialT(turma.getDataInicial().toDate());					
		}		
		TurmaReport report = new TurmaReport(turmas, "relTurmasGeral.jasper");		
		return reporter.pdfReport(report);
	}
	
	@Path("/turma/relFichaTecnica/{turmaid}") 
	public Download preparaFichaTecnica(Long turmaid){				
		
		List<Turma> turmas = turmaRepo.findByID(turmaid);
		
		for (Turma turma : turmas) {
			turma.setDataFinalT(turma.getDataFinal().toDate());
			turma.setDataInicialT(turma.getDataInicial().toDate());
			turma.getCurso().setMetodologia(turma.getCurso().getTipoCurso().getValue());
			if (turma.getCurso().getValidoAQ().equals(ValidoAQ.S)) {
				turma.getCurso().setValidoAQT("Sim");
			} else {
				turma.getCurso().setValidoAQT("Não");
			}
		}		
		TurmaReport report = new TurmaReport(turmas, "relFichaTecnicaTurma.jasper");		
		return reporter.pdfReport(report);
	}
	
}