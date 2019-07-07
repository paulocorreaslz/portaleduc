package br.jus.trema.portaleduc.controllers;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.jus.trema.portaleduc.enums.AppRole;
import br.jus.trema.portaleduc.models.Servidor;
import br.jus.trema.portaleduc.models.Turma;
import br.jus.trema.portaleduc.repositories.AlunoRepo;
import br.jus.trema.portaleduc.repositories.ProfessorRepo;
import br.jus.trema.portaleduc.repositories.ServidorRepo;
import br.jus.trema.portaleduc.repositories.TurmaRepo;
import br.jus.trema.vraptorbridge.authorization.annotations.Permission;
import br.jus.trema.vraptorbridge.messages.Messenger;
import br.jus.trema.vraptorbridge.pagination.Page;
import br.jus.trema.vraptorbridge.pagination.Paginator;

/**
 * 
 * @author Paulo Correa <paulo.correa@tre-ma.gov.br>
 *
 */
@Resource
@Permission(AppRole.ADMIN)
public class ServidorController {
	
	private final Result result;
	private final Validator validator;	
	private final ServidorRepo servidorRepo;
	private final TurmaRepo turmaRepo;
	private final AlunoRepo alunoRepo;
	private final ProfessorRepo professorRepo;	
	private final Messenger messenger;
	private final Paginator<Servidor> paginator;
	
	
	public ServidorController(Result result, Validator validator,TurmaRepo turmaRepo, AlunoRepo alunoRepo, ProfessorRepo professorRepo, ServidorRepo servidorRepo,  Messenger messenger) {
		this.result = result;
		this.validator = validator;		
		this.servidorRepo = servidorRepo;
		this.alunoRepo = alunoRepo;		
		this.turmaRepo = turmaRepo;
		this.professorRepo = professorRepo;
		this.messenger = messenger;
		this.paginator = new Paginator<Servidor>(servidorRepo);
	}
		
	@Get("/servidor")
	public Page<Servidor> list(Servidor servidor, int pagina) {
		result.include("servidor", servidor);						
		return paginator.paginate(servidor, pagina, "nome");
	}	
	
	@Get("/servidor/paginar/{pagina}")
	public void paginating(Servidor servidor, int pagina, Long turmaId) {
		result.include("turma", turmaRepo.find(turmaId));
		result.redirectTo(this).list(servidor, pagina);
	}
	
	@Get("/servidor/novoAluno/{turmaid}")
	public Page<Servidor> list(Servidor servidor, int pagina, Long turmaid) {
		result.include("servidor", servidor);		
		result.include("turma", turmaRepo.find(turmaid));		
		return paginator.paginate(servidor, pagina, "nome");
	}	
	
	@Get("/servidor/novoProfessor/{turmaid}")
	public Page<Servidor> lista(Servidor servidor, int pagina, Long turmaid) {
		result.include("servidor", servidor);		
		result.include("turma", turmaRepo.find(turmaid));		
		return paginator.paginate(servidor, pagina, "nome");
	}	
	
	@Get("/servidor/{user.id}")
	public Servidor show(Servidor servidor) {
		return servidorRepo.find(servidor.getId());
	}
	
	@Post("/servidor/busca")
	public void find(String matricula, String nome, Long turmaId) {
		Servidor servidor = new Servidor();
		if (matricula != null) servidor.setMatricula(matricula);
		if (nome != null) servidor.setNome(nome); 
		result.include("turma", turmaRepo.find(turmaId));
		result.redirectTo(this).list(servidor, 1, turmaId);
	}
	
	@Post("/servidor/buscaprofessor")
	public void localiza(String matricula, String nome, Long turmaId) {
		Servidor servidor = new Servidor();
		if (matricula != null) servidor.setMatricula(matricula);
		if (nome != null) servidor.setNome(nome); 
		result.include("turma", turmaRepo.find(turmaId));
		result.redirectTo(this).lista(servidor, 1, turmaId);
	}
}