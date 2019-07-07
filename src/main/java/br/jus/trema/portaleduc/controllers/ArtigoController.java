package br.jus.trema.portaleduc.controllers;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.jus.trema.portaleduc.enums.AppRole;
import br.jus.trema.portaleduc.models.Artigo;
import br.jus.trema.portaleduc.repositories.ArtigoRepo;
import br.jus.trema.vraptorbridge.authorization.annotations.Permission;
import br.jus.trema.vraptorbridge.messages.Messenger;
import br.jus.trema.vraptorbridge.pagination.Page;
import br.jus.trema.vraptorbridge.pagination.Paginator;
import br.jus.trema.vraptorbridge.util.Params;

/**
 * 
 * @author Paulo Correa <paulo.correa@tre-ma.gov.br>
 *
 */
@Resource
@Permission(AppRole.ADMIN)
public class ArtigoController {
	
	private final Result result;
	private final Validator validator;
	private final ArtigoRepo artigoRepo;
	private final Messenger messenger;
	private final Paginator<Artigo> paginator;
	
	
	public ArtigoController(Result result, Validator validator, ArtigoRepo artigoRepo, Messenger messenger) {
		this.result = result;
		this.validator = validator;
		this.artigoRepo = artigoRepo;
		this.messenger = messenger;
		this.paginator = new Paginator<Artigo>(artigoRepo);
	}
	
	@Get("/artigos")
	public Page<Artigo> index(Artigo artigo, int pagina) {
		result.include("artigo", artigo);
		return paginator.paginate(artigo, pagina, "dataCadastro", Params.DESC_ORDER);
	}
	
	@Post("/artigo/busca")
	public void find(String titulo, String dataCadastro) {
		Artigo artigo = new Artigo();
		if (titulo != null) artigo.setTitulo(titulo);
		if (dataCadastro != null) artigo.setDataCadastro(LocalDateTime.parse(dataCadastro, DateTimeFormat.forPattern("dd/MM/yyyy")));
		result.redirectTo(this).index(artigo, 1);
	}

	@Get("/artigo/paginar/{pagina}")
	public void paginating(Artigo artigo, int pagina) {
		result.redirectTo(this).index(artigo, pagina);
	}
	
	@Post("/artigo")
	public void create(Artigo artigo) {
		validator.validate(artigo);
		validator.onErrorUsePageOf(this).newArtigo();
		artigo.setDataCadastro(LocalDateTime.now());
		artigoRepo.save(artigo);
		messenger.addSuccess("Artigo salvo com sucesso!");
		result.redirectTo(this).show(artigo);
	}
	
	@Get("/artigo/novo")
	public Artigo newArtigo() {
		return new Artigo();
	}
	
	@Get("/artigo/{artigo.id}")
	public Artigo show(Artigo artigo) {
		return artigoRepo.find(artigo.getId());
	}
	
	@Put("/artigo")
	public void update(Artigo artigo) {
		validator.validate(artigo);
		validator.onErrorUsePageOf(this).edit(artigo);
		Artigo artigoNovo = artigoRepo.find(artigo.getId());
		artigoNovo.setTitulo(artigo.getTitulo());
		artigoNovo.setConteudo(artigo.getConteudo());
		artigoRepo.save(artigoNovo);
		result.redirectTo(this).show(artigoNovo);
	}
	
	@Get("/artigo/{artigo.id}/edita")
	public Artigo edit(Artigo artigo) {
		return artigoRepo.find(artigo.getId());
	}

	@Delete("/artigo/{artigo.id}")
	public void delete(Artigo artigo) {
		artigoRepo.delete(artigoRepo.find(artigo.getId()));
		result.redirectTo(this).index(null, 1);  
	}

}