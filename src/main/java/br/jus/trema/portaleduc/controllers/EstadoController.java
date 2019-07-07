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
import br.jus.trema.portaleduc.models.Estado;
import br.jus.trema.portaleduc.reports.EstadoReport;
import br.jus.trema.portaleduc.repositories.EstadoRepo;
import br.jus.trema.portaleduc.repositories.PaisRepo;
import br.jus.trema.vraptorbridge.authorization.annotations.Permission;
import br.jus.trema.vraptorbridge.pagination.Page;
import br.jus.trema.vraptorbridge.pagination.Paginator;
import br.jus.trema.vraptorbridge.reports.Reporter;

/**
 * 
 * @author Paulo Correa <paulo.correa@tre-ma.gov.br>
 *
 */
@Resource
@Permission({AppRole.JUIZ, AppRole.ADMINISTRADOR})
public class EstadoController {

	private final Result result;
	private final Reporter reporter;
	private final Validator validator;
	private final EstadoRepo estadoRepo;	
	private final PaisRepo paisRepo;
	private final Paginator<Estado> paginator;
	
	
	public EstadoController(Result result, Validator validator, Reporter reporter, EstadoRepo estadoRepo, PaisRepo paisRepo) {
		this.result = result;
		this.reporter = reporter;
		this.validator = validator;
		this.paisRepo = paisRepo;
		this.estadoRepo = estadoRepo;
		this.paginator = new Paginator<Estado>(estadoRepo);
	}

	@Get("/estado")
	public Page<Estado> index(int pagina) {
		return paginator.paginate(pagina, "nome");
	}
	
	@Get("/estado/paginar/{pagina}")
	public void paginating(int pagina) {
		result.redirectTo(this).index(pagina);
	}
		
	@Post("/estado")
	public void create(Estado estado) {
		result.include("paisList", paisRepo.findAll("nome"));
		validator.validate(estado);
		validator.onErrorUsePageOf(this).newEstado();
		estadoRepo.save(estado);
		result.redirectTo(this).show(estado);
	}
	
	@Get("/estado/novo")
	public Estado newEstado() {
		result.include("paisList", paisRepo.findAll("nome"));
		return new Estado();
	}
	
	@Put("/estado")
	public void update(Estado estado) {
		validator.validate(estado);
		validator.onErrorUsePageOf(this).edit(estado);
		estadoRepo.save(estado);
		result.redirectTo(this).show(estado);
	}
	
	@Get("/estado/{estado.id}/edita")
	public Estado edit(Estado estado) {
		result.include("paisList", paisRepo.findAll("nome"));
		return estadoRepo.find(estado.getId());
	}

	@Get("/estado/{estado.id}")
	public Estado show(Estado estado) {
		return estadoRepo.find(estado.getId());
	}

	@Delete("/estado/{estado.id}")
	public void delete(Estado estado) {
		estadoRepo.delete(estadoRepo.find(estado.getId()));
		result.redirectTo(this).index(1);  
	}
	
	@Path("/estado/report") 
	public Download report() {
		List<Estado> estados = estadoRepo.findAll("nome");
		EstadoReport report = new EstadoReport(estados, "estados.jasper");
		return reporter.pdfReport(report);
	}
}