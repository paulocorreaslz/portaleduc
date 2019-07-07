package br.jus.trema.portaleduc.controllers;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.jus.trema.portaleduc.enums.AppRole;
import br.jus.trema.portaleduc.models.Pais;
import br.jus.trema.portaleduc.repositories.PaisRepo;
import br.jus.trema.vraptorbridge.authorization.annotations.Permission;
import br.jus.trema.vraptorbridge.pagination.Page;
import br.jus.trema.vraptorbridge.pagination.Paginator;

/**
 * 
 * @author Paulo Correa <paulo.correa@tre-ma.gov.br>
 *
 */
@Resource
public class PaisController {
	
	private final Result result;
	private final Validator validator;
	private final PaisRepo paisRepo;
	private final Paginator<Pais> paginator;
	
	
	public PaisController(Result result, Validator validator, PaisRepo paisRepo) {
		this.result = result;
		this.validator = validator;
		this.paisRepo = paisRepo;
		this.paginator = new Paginator<Pais>(paisRepo);
	}
		
	@Get("/pais")
	public Page<Pais> index(Pais pais, int pagina) {
		result.include("pais", pais);
		return paginator.paginate(pais, pagina, "nome");
	}
	
	@Post("/pais/busca")
	public void find(Pais pais) {
		result.redirectTo(this).index(pais, 1);
	}
	
	@Get("/pais/paginar/{pagina}")
	public void paginating(Pais pais, int pagina) {
		result.redirectTo(this).index(pais, pagina);
	}
	
	@Post("/pais")
	@Permission(AppRole.ADMINISTRADOR)
	public void create(Pais pais) {
		validator.validate(pais);
		validator.onErrorUsePageOf(this).newPais();
		paisRepo.save(pais);
		result.redirectTo(this).show(pais);
	}
	
	@Get("/pais/novo")
	@Permission(AppRole.ADMINISTRADOR)
	public Pais newPais() {
		return new Pais();
	}
	
	@Put("/pais")
	@Permission(AppRole.ADMINISTRADOR)
	public void update(Pais pais) {
		validator.validate(pais);
		validator.onErrorUsePageOf(this).edit(pais);
		paisRepo.save(pais);
		result.redirectTo(this).show(pais);
	}
	
	@Get("/pais/{pais.id}/edita")
	@Permission(AppRole.ADMINISTRADOR)
	public Pais edit(Pais pais) {
		return paisRepo.find(pais.getId());
	}

	@Get("/pais/{pais.id}")
	@Permission({AppRole.ADMINISTRADOR, AppRole.ZONA})
	public Pais show(Pais pais) {
		return paisRepo.find(pais.getId());
	}

	@Delete("/pais/{pais.id}")
	@Permission(AppRole.ZONA)
	public void delete(Pais pais) {
		paisRepo.delete(paisRepo.find(pais.getId()));
		result.redirectTo(this).index(null, 1);  
	}
}