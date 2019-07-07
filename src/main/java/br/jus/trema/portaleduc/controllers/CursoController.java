package br.jus.trema.portaleduc.controllers;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

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
import br.jus.trema.portaleduc.enums.TipoCurso;
import br.jus.trema.portaleduc.enums.ValidoAQ;
import br.jus.trema.portaleduc.enums.VersaoMoodle;
import br.jus.trema.portaleduc.models.Aluno;
import br.jus.trema.portaleduc.models.Curso;
import br.jus.trema.portaleduc.models.Servidor;
import br.jus.trema.portaleduc.models.Turma;
import br.jus.trema.portaleduc.reports.CursoReport;
import br.jus.trema.vraptorbridge.reports.Reporter;
import br.jus.trema.portaleduc.repositories.CursoRepo;
import br.jus.trema.vraptorbridge.authorization.annotations.Permission;
import br.jus.trema.vraptorbridge.messages.Messenger;
import br.jus.trema.vraptorbridge.pagination.Page;
import br.jus.trema.vraptorbridge.pagination.Paginator;
import br.jus.trema.vraptorbridge.repositories.UserRepo;

/**
 * 
 * @author Paulo Correa <paulo.correa@tre-ma.gov.br>
 *
 */
@Resource
@Permission(AppRole.ADMIN)
public class CursoController {
	
	private final Result result;
	private final Validator validator;
	private final UserRepo userRepo;
	private final CursoRepo cursoRepo;
	private final Messenger messenger;
	private final Reporter reporter;
	private final Paginator<Curso> paginator;
	
	
	public CursoController(Result result, Validator validator, UserRepo userRepo, CursoRepo cursoRepo,  Messenger messenger, Reporter reporter) {
		this.result = result;
		this.validator = validator;
		this.userRepo = userRepo;
		this.cursoRepo = cursoRepo;
		this.messenger = messenger;
		this.reporter = reporter;
		this.paginator = new Paginator<Curso>(cursoRepo);
	}
	
	
	@Get("/cursos")
	public Page<Curso> list(Curso curso, int pagina) {
		result.include("curso", curso);
		return paginator.paginate(curso, pagina, "nome");
	}
	
	@Get("/curso/novo")
	public Curso newCurso() {
		buildLists();
		return new Curso();
	}
	
	@Get("/curso/paginar/{pagina}")
	public void paginating(Curso curso, int pagina) {
		result.redirectTo(this).list(curso, pagina);
	}
	
	@Get("/curso/{curso.id}")
	public Curso show(Curso curso) {
		return cursoRepo.find(curso.getId());
	}
	
	@Post("/curso")
	public void create(Curso curso) {
		buildLists();
		curso.setDataCadastro(DateTime.now());		
		validator.validate(curso);
		validator.onErrorUsePageOf(this).newCurso();
		cursoRepo.save(curso);
		messenger.addSuccess("Curso salvo com sucesso!");
		result.redirectTo(this).show(curso);
	}
	
	@Get("/curso/{curso.id}/edita")
	public Curso edit(Curso curso) {		
		buildLists();
		return cursoRepo.find(curso.getId());
	}

	@Delete("/curso/{curso.id}")
	public void delete(Curso curso) {
		cursoRepo.delete(cursoRepo.find(curso.getId()));
		messenger.addSuccess("Curso excluído com sucesso.");
		result.forwardTo(this).list(null, 1);  
	}
	
	@Post("/curso/busca")
	public void find(String nome,String dataCadastro) {
		Curso curso = new Curso();
		if (nome != null) curso.setNome(nome);
		if (dataCadastro != null) curso.setDataCadastro(DateTime.parse(dataCadastro, DateTimeFormat.forPattern("dd/MM/yyyy"))); 
		result.redirectTo(this).list(curso, 1);
	}
	
	
	@Put("/curso")
	public void update(Curso curso) {
		buildLists();
		validator.validate(curso);
		validator.onErrorUsePageOf(this).edit(curso);
		Curso cursoNovo = cursoRepo.find(curso.getId());
		cursoNovo.setNome(curso.getNome());
		cursoNovo.setEleitoral(curso.getEleitoral());
		cursoNovo.setTipoCurso(curso.getTipoCurso());
		cursoNovo.setVersaoMoodle(curso.getVersaoMoodle());		
		cursoNovo.setObjetivo(curso.getObjetivo());
		cursoNovo.setJustificativa(curso.getJustificativa());
		cursoNovo.setDataCadastro(curso.getDataCadastro());	
		cursoNovo.setValidoAQ(curso.getValidoAQ());
		cursoRepo.save(cursoNovo);
		messenger.addSuccess("As informações do curso foram atualizadas com sucesso.");
		result.forwardTo(this).show(cursoNovo);
	}
	
	@Path("/curso/relatorioCursosGeral") 
	public Download report() {
		List<Curso> cursos = cursoRepo.findAll("nome");
		CursoReport report = new CursoReport(cursos, "relCursosGeral.jasper");
		return reporter.pdfReport(report);
	}
	
	@Get("/curso/relatorioGeral")
	public Curso relatorio() {
		return new Curso();
	}
	
	@Post("/curso/relatorioGeral")
	public Download preparaRelatorio(String nome, String dataCadastro){	
		List<Curso> meusCursos = cursoRepo.findByName(nome, dataCadastro);
		
		for (Curso curso : meusCursos) {
			curso.setDataCadastroT(curso.getDataCadastro().toDate());								
		}
		
		CursoReport report = new CursoReport(meusCursos, "relCursosGeral.jasper");		
		return reporter.pdfReport(report);
	}
	
	private void buildLists() {
		result.include("tipoCursoList", Arrays.asList(TipoCurso.values()));		
		result.include("eleitoralList", Arrays.asList(Eleitoral.values()));
		result.include("validoAQList", Arrays.asList(ValidoAQ.values()));	
		result.include("versaoMoodleList", Arrays.asList(VersaoMoodle.values()));
	}
}