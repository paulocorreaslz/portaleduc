package br.jus.trema.portaleduc.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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
import br.jus.trema.portaleduc.enums.SituacaoAluno;
import br.jus.trema.portaleduc.enums.TipoProfessor;
import br.jus.trema.portaleduc.models.Aluno;
import br.jus.trema.portaleduc.models.Professor;
import br.jus.trema.portaleduc.models.Servidor;
import br.jus.trema.portaleduc.models.Turma;
import br.jus.trema.portaleduc.reports.ProfessorReport;
import br.jus.trema.portaleduc.repositories.LotacaoRepo;
import br.jus.trema.portaleduc.repositories.ProfessorRepo;
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
public class ProfessorController {
	
	private final Result result;
	private final Validator validator;	
	private final ProfessorRepo professorRepo;
	private final ServidorRepo servidorRepo;
	private final LotacaoRepo lotacaoRepo;
	private final TurmaRepo turmaRepo;
	private final Messenger messenger;
	private final Reporter reporter;
	private final AuthenticationControl authenticationControl;
	private final Paginator<Professor> paginator;
	private final Paginator<Turma> turmaPaginator;
	
	
	public ProfessorController(Result result,  AuthenticationControl authenticationControl, Reporter reporter, LotacaoRepo lotacaoRepo, Validator validator,ServidorRepo servidorRepo, TurmaRepo turmaRepo, ProfessorRepo professorRepo,  Messenger messenger) {
		this.result = result;
		this.reporter = reporter;
		this.validator = validator;		
		this.authenticationControl = authenticationControl;
		this.professorRepo = professorRepo;
		this.servidorRepo = servidorRepo;
		this.lotacaoRepo = lotacaoRepo;
		this.turmaRepo = turmaRepo;
		this.messenger = messenger;
		this.paginator = new Paginator<Professor>(professorRepo);
		this.turmaPaginator = new Paginator<Turma>(turmaRepo);
	}
	
	
	@Get("/professor")
	public Page<Professor> list(Professor professor, int pagina) {
		result.include("professor", professor);
		return paginator.paginate(professor, pagina, "nome");
	}
	
	
	@Get("/professor/novo/{servidorid}/{turmaid}")
	public Professor newProfessor(Long servidorid, Long turmaid) {
		buildLists();
		Turma turma = turmaRepo.find(turmaid);
		Servidor servidor = servidorRepo.find(servidorid);		
		Professor professor = new Professor(turma);
		if (servidor.getLotacao() == null) {
			professor.setLotacao(servidor.getSituacao());
		}else {
			professor.setLotacao(servidor.getLotacao());
		}			
		professor.setMatricula(servidor.getMatricula());		
		professor.setNome(servidor.getNome());
		professor.setSituacao(servidor.getSituacao());		
		result.include("servidor", servidor);
		result.include("turma", turma);
		return professor;
	}
	
	@Get("/professor/paginar/{pagina}")
	public void paginating(Professor professor, int pagina) {
		result.redirectTo(this).list(professor, pagina);
	}
	
	@Get("/professor/{professor.id}")
	public Professor show(Professor professor) {
		Turma turma = professor.getTurma();
		result.include("turma", turma);
		return professorRepo.find(professor.getId());
	}
	
	
	@Post("/professor")
	public void create(Professor professor, Long servidorid, Long turmaid) {
		buildLists();
		professor.setDataCadastro(DateTime.now());		
		validator.validate(professor);		
		professor.setTurma(turmaRepo.find(turmaid));
		validator.onErrorUsePageOf(this).newProfessor(servidorid, turmaid);
		professorRepo.save(professor);
		messenger.addSuccess("Professor salvo com sucesso!");
		result.redirectTo(this).show(professor);
	}
	

	@Get("/professor/turma/{turmaid}")
	public void professorTurma(Long turmaid) {
		Professor professor = new Professor();
		if (turmaid != null) {
			professor.setTurma(turmaRepo.find(turmaid));			
		}			
		result.include("turma", turmaRepo.find(turmaid));
		result.redirectTo(this).list(professor, 1);
	}

	@Post("/professor/busca")
	public void find(String matricula, String nome, Long turmaid) {
		Professor professor = new Professor();
		if (matricula != null) professor.setMatricula(matricula);
		if (nome != null) professor.setNome(nome); 
		if (turmaid != null) {
			professor.setTurma(turmaRepo.find(turmaid));
			result.include("turma", turmaRepo.find(turmaid));
		}
		result.redirectTo(this).list(professor, 1);
	}
	
	@Get("/professor/{professor.id}/edita")
	public Professor edit(Professor professor) {
		buildLists();
		return professorRepo.find(professor.getId());
	}
	
	@Delete("/professor/{professor.id}")
	public void delete(Professor professor) {
		Long turmaid = (Long) professorRepo.find(professor.getId()).getTurma().getId();		
		professorRepo.delete(professorRepo.find(professor.getId()));						
		messenger.addSuccess("O professor foi excluído com sucesso!");
		result.forwardTo(this).professorTurma(turmaid);  
	}
	
	@Put("/professor")
	public void update(Professor professor) {
		buildLists();		
		Professor professorNovo = professorRepo.find(professor.getId());
		professorNovo.setSituacao(professor.getSituacao());
		professorNovo.setLotacao(professor.getLotacao());
		professorNovo.setTipoProfessor(professor.getTipoProfessor());
		professorNovo.setMatricula(professor.getMatricula());
		professorNovo.setNome(professor.getNome());
		professorNovo.setDataCadastro(professorNovo.getDataCadastro());
		validator.validate(professorNovo);
		validator.onErrorUsePageOf(this).edit(professorNovo);
		professorRepo.save(professorNovo);
		messenger.addSuccess("As informações do professor foram alteradas com sucesso!");		
		result.forwardTo(this).show(professorNovo);
	}
	
	@Path("/professor/relProfessoresTurma/{turmaid}") 
	public Download preparaFichaTecnica(Long turmaid){				
		
		List<Professor> professores = professorRepo.findByTurma(turmaRepo.find(turmaid));
		
		for (Professor professor : professores) {
			
			if (professor.getTipoProfessor().equals(TipoProfessor.A)) professor.setTipoProfessorT("Acompanhamento");
			if (professor.getTipoProfessor().equals(TipoProfessor.C)) professor.setTipoProfessorT("Conteúdo");
			if (professor.getTipoProfessor().equals(TipoProfessor.T)) professor.setTipoProfessorT("Tecnico");
			
			professor.getTurma().setDataInicialT(professor.getTurma().getDataInicial().toDate());
			professor.getTurma().setDataFinalT(professor.getTurma().getDataFinal().toDate());
			
		}		
		ProfessorReport report = new ProfessorReport(professores, "relProfessoresTurma.jasper");		
		return reporter.pdfReport(report);
	}
	
	@Permission(AppRole.TRE)
	@Get("/professor/meuscursos")
	public Page<Turma> minhasTurmas(int pagina) {
		Professor professor = new Professor();
		professor.setMatricula(authenticationControl.getLoggedUser().getId());
		List<Professor> professores = new ArrayList<Professor>();
		professores.add(professor);
		
		Turma turma = new Turma();
		turma.setProfessores(professores);
		
		return turmaPaginator.paginate(turma, pagina, "dataInicial", Params.DESC_ORDER);		
	}
	
	@Permission(AppRole.TRE)
	@Get("/professor/paginateTurmas/{pagina}")
	public Page<Turma> paginateTurmas(int pagina) {
		return minhasTurmas(pagina);
	}
		
	@Post("/professor/localizarCursos")
	public Page<Turma> localizarTurmas(int pagina, String matricula) {
		List<Professor> professorMat = professorRepo.findByMatricula(matricula);		
		Professor professor = new Professor();
		
		if (professorMat.size() > 0) {
			professor = professorMat.get(0);
		} else {
			professor.setMatricula(matricula.trim());
		}			
		List<Professor> professores = new ArrayList<Professor>();
		professores.add(professor);
		
		Turma turma = new Turma();
		turma.setProfessores(professores);
		
		result.include("professor", professor);
		return turmaPaginator.paginate(turma, pagina, "dataInicial", Params.DESC_ORDER);		
	}
	
	@Get("/professor/paginateTurmas2/{pagina}")
	public Page<Turma> paginateTurmas2(int pagina) {
		return localizarTurmas(pagina, null);
	}
	
	@Get("/professor/verCursos")
	public void verCursos() {
		messenger.addInformation("Digite a matricula do professor para localizar os cursos que o aluno participou!");		
	}
	
	@Permission(AppRole.TRE)
	@Get("/professor/minhasdeclaracoes")
	public Page<Professor> meusCertificados(int pagina) {
		Professor professor = new Professor();		
		professor.setMatricula(authenticationControl.getLoggedUser().getId());	
		return paginator.paginate(professor, pagina);
		//result.redirectTo(this).meusCertificados(aluno, 1);
	}
	
	@Permission(AppRole.TRE)
	@Get("/professor/paginarProfessores/{pagina}")
	public void paginatingCertificados(int pagina) {
		result.redirectTo(this).meusCertificados(pagina);
	}
	
	@Get("/professor/calculohorasanual")
	public void calcularHoras(){
		messenger.addInformation("Insira a matricula do professor e a data especifica para calcular as horas do professor no ano.");
	}
	
	@Post("/professor/calcularhorasprofessor")
	public void horasProfessor(String matricula) {
		String resultado = "";
		ArrayList<String> resultados = new ArrayList<String>();	
		Calendar calendar = Calendar.getInstance();					
		int anoInicial = 2007;
		int anoAtual = (int) calendar.get(Calendar.YEAR);
		Professor professorOut = null;
		for (anoInicial = 2007; anoInicial <= anoAtual; anoInicial++){
			String data = "01/01/"+anoInicial;			
			Date dataConsulta = null;
			try {
				dataConsulta = new SimpleDateFormat("dd/mm/yyyy").parse(data);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("data: "+dataConsulta);			
			List<Professor> professores = professorRepo.getHorasProfessorPeriodo(matricula, dataConsulta);
			int total = 0;
			for (Professor professor : professores){
				total = total + Integer.parseInt(professor.getTurma().getCargaHoraria());
				System.out.println("["+ anoInicial+"] Horas da turma = "+ total);	
				professorOut = professor;
			}
			resultado = "["+anoInicial+ "] Horas totais no ano: ["+total+"]";
			System.out.println(resultado);
			resultados.add(resultado);
		}
		//System.out.println(resultados);
		result.include("professor", professorOut);
		result.include("resultados", resultados);
	}
	
	private void buildLists() {
		result.include("TipoProfessorList", Arrays.asList(TipoProfessor.values()));		
	}
}