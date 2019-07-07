package br.jus.trema.portaleduc.controllers;

import java.util.Calendar;
import java.util.List;

import org.joda.time.DateTime;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.interceptor.download.Download;
import br.jus.trema.portaleduc.enums.AppRole;
import br.jus.trema.portaleduc.enums.SituacaoAluno;
import br.jus.trema.portaleduc.models.Aluno;
import br.jus.trema.portaleduc.models.Certificado;
import br.jus.trema.portaleduc.models.Professor;
import br.jus.trema.portaleduc.models.Turma;
import br.jus.trema.portaleduc.reports.CertificadoReport;
import br.jus.trema.portaleduc.repositories.AlunoRepo;
import br.jus.trema.portaleduc.repositories.CertificadoRepo;
import br.jus.trema.portaleduc.repositories.ProfessorRepo;
import br.jus.trema.portaleduc.repositories.ServidorRepo;
import br.jus.trema.portaleduc.repositories.TurmaRepo;
import br.jus.trema.vraptorbridge.authorization.annotations.Permission;
import br.jus.trema.vraptorbridge.messages.Messenger;
import br.jus.trema.vraptorbridge.pagination.Page;
import br.jus.trema.vraptorbridge.pagination.Paginator;
import br.jus.trema.vraptorbridge.reports.Reporter;

/**
 * 
 * @author Paulo Correa <paulo.correa@tre-ma.gov.br>
 *
 */
@Resource
@Permission(AppRole.ADMIN)
public class CertificadoController {
	
	private final Result result;
	private final Validator validator;	
	private final ServidorRepo servidorRepo;
	private final TurmaRepo turmaRepo;
	private final AlunoRepo alunoRepo;
	private final ProfessorRepo professorRepo;	
	private final Messenger messenger;
	private final Reporter reporter;
	private final CertificadoRepo certificadoRepo;
	private final Paginator<Certificado> paginator;
	
	
	public CertificadoController(Result result, Reporter reporter, CertificadoRepo certificadoRepo, Validator validator,TurmaRepo turmaRepo, AlunoRepo alunoRepo, ProfessorRepo professorRepo, ServidorRepo servidorRepo,  Messenger messenger) {
		this.result = result;
		this.validator = validator;		
		this.servidorRepo = servidorRepo;
		this.reporter = reporter;
		this.alunoRepo = alunoRepo;		
		this.turmaRepo = turmaRepo;
		this.professorRepo = professorRepo;
		this.messenger = messenger;
		this.certificadoRepo = certificadoRepo;
		this.paginator = new Paginator<Certificado>(certificadoRepo);
	}
		
	
	@Get("/certificados")
	public Page<Certificado> list(Certificado certificado, int pagina) {
		result.include("certificado", certificado);						
		return paginator.paginate(certificado, pagina, "codigo");
	}	
	
	@Get("/certificado/paginar/{pagina}")
	public void paginating(Certificado certificado, int pagina) {
		result.redirectTo(this).list(certificado, pagina);
	}
	
	@Get("/certificado/novoAlunoProfessor/{turmaid}")
	public Page<Certificado> list(Certificado certificado, int pagina, Long turmaid) {
		result.include("certificado", certificado);		
		result.include("turma", turmaRepo.find(turmaid));		
		return paginator.paginate(certificado, pagina, "codigo");
	}	
			
	@Get("/certificado/{certificado.id}")
	public Certificado show(Certificado certificado) {	
		result.include("turma", turmaRepo.find(certificado.getTurma().getId()));
		result.include("curso", turmaRepo.find(certificado.getTurma().getCurso().getId()));
		return certificadoRepo.find(certificado.getId());
	}

	@Permission(AppRole.TRE)
	@Get("/certificado/aluno/{certificadoid}")
	public Certificado ver(Long certificadoid) {
		Certificado certificado = certificadoRepo.find(certificadoid);
		certificado.setDataImpressao(DateTime.now());						
		certificadoRepo.save(certificado);
		result.include("turma", turmaRepo.find(certificado.getTurma().getId()));
		result.include("curso", turmaRepo.find(certificado.getTurma().getCurso().getId()));
		return certificadoRepo.find(certificado.getId());
	}
	
	@Permission(AppRole.TRE)
	@Get("/certificado/aluno/")
	public void erro(){		
		messenger.addError("O certificado não foi encontrado ou não foi liberado!");
		result.forwardTo(AlunoController.class).meusCertificados(1);
	}
			
	
	@Get("/certificado/CertificadoAluno/{alunoid}/{turmaid}")
	public void newCertificado(Long alunoid, Long turmaid) {
		Turma turma = turmaRepo.find(turmaid);	
		Aluno aluno = alunoRepo.find(alunoid);			
		if (certificadoRepo.getCertificado(turma, aluno) == null && aluno.getSituacao().equals(SituacaoAluno.A)) {			
			Certificado certificado = new Certificado();
			certificado.setAluno(aluno);
			certificado.setTurma(turma);
			gerar(certificado, alunoid, null, turmaid);			
		} else {
			if (!aluno.getSituacao().equals(SituacaoAluno.A)) {
				messenger.addError("Impossível criar certificado, aluno não aprovado!");
				result.forwardTo(AlunoController.class).show(aluno);
			} else {
				result.redirectTo(this).show(certificadoRepo.getCertificado(turma, aluno));
			}
		}		
	}
	
	@Get("/certificado/CertificadoProfessor/{professorid}/{turmaid}")
	public void newDeclaracao(Long professorid, Long turmaid) {
		Turma turma = turmaRepo.find(turmaid);	
		Professor professor = professorRepo.find(professorid);			
		if (certificadoRepo.getCertificado(turma, professor) != null) {
			result.redirectTo(this).show(certificadoRepo.getCertificado(turma, professor));						
		} else {
			Certificado certificado = new Certificado();
			certificado.setProfessor(professor);
			certificado.setTurma(turma);
			gerar(certificado, null, professorid, turmaid);
		}		
	}
	
	@Get("/turma/liberarCertificadosAlunos/{turmaid}")
	public Turma liberarCertificadosAlunos(Long turmaid) {
		
		Turma turma = turmaRepo.find(turmaid);
		List<Aluno> alunos =turma.getAlunos(); 
		int count = 0;
		for (Aluno aluno: alunos){
			
			Certificado certificado = new Certificado();			
			certificado.setDataCadastro(DateTime.now());						
			certificado.setTurma(turmaRepo.find(turmaid));
			
			if (certificadoRepo.getCertificado(turma, aluno) == null && aluno.getSituacao().equals(SituacaoAluno.A)) {	
				
				Calendar data = Calendar.getInstance();				
				String DataOk = "" + data.getTime().getYear()+ "" + data.getTime().getMonth() +data.getTime().getDay() + data.getTime().getHours() + data.getTime().getMinutes();		
				String codigo = "";
				
				if (aluno != null){
					certificado.setAluno(alunoRepo.find(aluno.getId()));
					codigo = DataOk +"-"+ turmaRepo.find(turmaid).getCurso().getId() + "-" + turmaid + "-" + aluno.getId() + "-"+ alunoRepo.find(aluno.getId()).getMatricula();	
				}
						
				certificado.setCodigo(codigo);
				validator.validate(certificado);
				
				if (aluno != null )
					validator.onErrorUsePageOf(this).newCertificado(aluno.getId(), turmaid);
				
				certificadoRepo.save(certificado);
				count++;
			} 				
		}
		if (count == 1 )
			messenger.addSuccess("Atenção: Foi liberado ["+ count +"] Certificado!");
		if (count > 1)
			messenger.addSuccess("Atenção: Foram liberados ["+ count +"] Certificados!");
		if (count == 0)
			messenger.addInformation("Atenção: Nenhum certificado liberado!");
		
		return result.forwardTo(TurmaController.class).show(turmaRepo.find(turmaid));
	}

	@Get("/turma/liberarDeclaracoesProfessores/{turmaid}")
	public Turma liberarCertificadosProfessores(Long turmaid) {
		
		Turma turma = turmaRepo.find(turmaid);
		List<Professor> professores =turma.getProfessores(); 
		int count = 0;
		for (Professor professor: professores){
			
			Certificado certificado = new Certificado();			
			certificado.setDataCadastro(DateTime.now());						
			certificado.setTurma(turmaRepo.find(turmaid));
			
			if (certificadoRepo.getCertificado(turma, professor) == null) {	
				
				Calendar data = Calendar.getInstance();				
				String DataOk = "" + data.getTime().getYear()+ "" + data.getTime().getMonth() +data.getTime().getDay() + data.getTime().getHours() + data.getTime().getMinutes();		
				String codigo = "";
				
				if (professor != null){
					certificado.setProfessor(professorRepo.find(professor.getId()));
					codigo = DataOk +"-"+ turmaRepo.find(turmaid).getCurso().getId() + "-" + turmaid + "-" + professor.getId() + "-"+ professorRepo.find(professor.getId()).getMatricula();	
				}
						
				certificado.setCodigo(codigo);
				validator.validate(certificado);
				
				if (professor != null )
					validator.onErrorUsePageOf(this).newCertificado(professor.getId(), turmaid);
				
				certificadoRepo.save(certificado);
				count++;
			} 				
		}
		if (count == 1 )
			messenger.addSuccess("Atenção: Foi liberado ["+ count +"] Declaração!");
		if (count > 1)
			messenger.addSuccess("Atenção: Foram liberados ["+ count +"] Declarações!");
		if (count == 0)
			messenger.addSuccess("Atenção: Nenhuma Declaração liberada!");
		
		return result.forwardTo(TurmaController.class).show(turmaRepo.find(turmaid));
	}
		
	public void gerar(Certificado certificado, Long alunoid, Long professorid, Long turmaid) {
		
		certificado.setDataCadastro(DateTime.now());						
		certificado.setTurma(turmaRepo.find(turmaid));
		
		Calendar data = Calendar.getInstance();				
		String DataOk = "" + data.getTime().getYear()+ "" + data.getTime().getMonth() +data.getTime().getDay() + data.getTime().getHours() + data.getTime().getMinutes();		
		String codigo = "";
		
		if (alunoid != null){
			certificado.setAluno(alunoRepo.find(alunoid));			
			codigo = DataOk +"-"+ turmaRepo.find(turmaid).getCurso().getId() + "-" + turmaid + "-" + alunoid + "-"+ alunoRepo.find(alunoid).getMatricula();	
		}
		
		if (professorid != null) {
			certificado.setProfessor(professorRepo.find(professorid));
			codigo = DataOk+"-"+ turmaRepo.find(turmaid).getCurso().getId() + "-" + turmaid + "-" + professorid + "-"+ professorRepo.find(professorid).getMatricula();	

		}		
		
		certificado.setCodigo(codigo);
		validator.validate(certificado);
		
		if (alunoid != null ) {
			validator.onErrorUsePageOf(this).newCertificado(alunoid, turmaid);
			Aluno aluno = alunoRepo.find(alunoid);
			aluno.setCertificado(certificado);
			alunoRepo.update(aluno);			
		}
		
		if (professorid != null ) {
			validator.onErrorUsePageOf(this).newDeclaracao(professorid, turmaid);						
			Professor professor = professorRepo.find(professorid);
			professor.setCertificado(certificado);
			professorRepo.update(professor);
		}
					
		certificadoRepo.save(certificado);
		messenger.addSuccess("Certificado/Declaração salvo(a) com sucesso!");		
		result.forwardTo(this).show(certificado);
	}
	
	@Permission(AppRole.TRE)
	@Path("/certificado/verCertificado/{certificadoid}")
	public Download preparaCertificado(Long certificadoid){	
		List<Certificado> certificados = certificadoRepo.findByID(certificadoRepo.find(certificadoid).getTurma(),certificadoRepo.find(certificadoid).getAluno());
		for (Certificado certificado : certificados) {
			certificado.getTurma().setDataInicialT(certificado.getTurma().getDataInicial().toDate());
			certificado.getTurma().setDataFinalT(certificado.getTurma().getDataFinal().toDate());
			
		}	
		CertificadoReport report = new CertificadoReport(certificados, "relCertificadoAluno.jasper");		
		return reporter.pdfReport(report);
	}
	
	@Permission(AppRole.TRE)
	@Path("/certificado/verDeclaracao/{certificadoid}")
	public Download preparaDeclaraaco(Long certificadoid){	
		List<Certificado> certificados = certificadoRepo.findByIDProfessor(certificadoRepo.find(certificadoid).getTurma(),certificadoRepo.find(certificadoid).getProfessor());
		for (Certificado certificado : certificados) {
			certificado.getTurma().setDataInicialT(certificado.getTurma().getDataInicial().toDate());
			certificado.getTurma().setDataFinalT(certificado.getTurma().getDataFinal().toDate());
			
		}	
		CertificadoReport report = new CertificadoReport(certificados, "relDeclaracaoProfessor.jasper");		
		return reporter.pdfReport(report);
	}
	
	@Permission(AppRole.TRE)
	@Path("/turma/downloadCertificados/{turmaid}")
	public Download downloadCertificados(Long turmaid){		
		Turma turma = turmaRepo.find(turmaid);
		List<Certificado> certificados = turma.getCertificados();
		for (Certificado certificado1 : certificados) {
			certificado1.getTurma().setDataInicialT(certificado1.getTurma().getDataInicial().toDate());
			certificado1.getTurma().setDataFinalT(certificado1.getTurma().getDataFinal().toDate());
			
		}	
		CertificadoReport report = new CertificadoReport(certificados, "relCertificadoAluno.jasper");						
		return reporter.pdfReport(report);
	}
	
	@Permission({AppRole.TRE})
	@Get("/certificado/{certificado.id}/imagemCertificado")
	public Download showReport(Certificado certificado) {
		List<Certificado> certificados = certificadoRepo.findByID(certificadoRepo.find(certificado.getId()).getTurma(),certificadoRepo.find(certificado.getId()).getAluno());
		for (Certificado certificado1 : certificados) {
			certificado1.getTurma().setDataInicialT(certificado1.getTurma().getDataInicial().toDate());
			certificado1.getTurma().setDataFinalT(certificado1.getTurma().getDataFinal().toDate());
			
		}	
		CertificadoReport certificadoReport = new CertificadoReport(certificados,"relCertificadoAluno.jasper");
		return reporter.jpegReport(certificadoReport);
	}
	
	@Permission({AppRole.TRE})
	@Get("/certificado/{certificado.id}/imagemDeclaracao")
	public Download showReportDeclaracao(Certificado certificado) {
		List<Certificado> certificados = certificadoRepo.findByIDProfessor(certificadoRepo.find(certificado.getId()).getTurma(),certificadoRepo.find(certificado.getId()).getProfessor());
		for (Certificado certificado1 : certificados) {
			certificado1.getTurma().setDataInicialT(certificado1.getTurma().getDataInicial().toDate());
			certificado1.getTurma().setDataFinalT(certificado1.getTurma().getDataFinal().toDate());			
		}	
		CertificadoReport report = new CertificadoReport(certificados, "relDeclaracaoProfessor.jasper");					
		return reporter.jpegReport(report);
	}

	@Permission(AppRole.TRE)
	public void validar() {	
		messenger.addInformation("Digite o código de validação do certificado para validar o mesmo em nossa base da dados.");				
	}
	
	
	@Permission(AppRole.TRE)
	@Post("/certificado/busca")
	public void find(String codigo) {				
		// o redirect perde o contexto, pra manter as paradas tem que ser o forward!
		if (codigo == null || codigo == "") {
			messenger.addInformation("Código inválido ou nulo! Por favor digite o código de validação.");
			result.forwardTo(this).validar();
		} else  {		
			Certificado certificado = certificadoRepo.findByCodigo(codigo);							
			if (certificado != null) {
				messenger.addInformation("Este certificado é válido!!");				
				result.forwardTo(this).show(certificado); 
			} else {
				messenger.addInformation("Certificado não encontrado! Digite novamente o código de validação.");
				result.forwardTo(this).validar();	
			}
		}			
	}	
}