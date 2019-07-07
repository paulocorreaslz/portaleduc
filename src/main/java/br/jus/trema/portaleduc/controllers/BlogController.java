package br.jus.trema.portaleduc.controllers;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.jus.trema.portaleduc.models.Artigo;
import br.jus.trema.portaleduc.repositories.ArtigoRepo;
import br.jus.trema.vraptorbridge.authentication.annotations.Public;
import br.jus.trema.vraptorbridge.pagination.Page;
import br.jus.trema.vraptorbridge.pagination.Paginator;
import br.jus.trema.vraptorbridge.util.Params;

@Resource
public class BlogController {

	private final Result result;
	private final ArtigoRepo artigoRepo;
	private final Paginator<Artigo> paginator;
	
	public BlogController(Result result, ArtigoRepo artigoRepo) {
		this.result = result;
		this.artigoRepo = artigoRepo;
		this.paginator = new Paginator<Artigo>(artigoRepo);
	}

	@Public
	@Get("/blog")
	public Page<Artigo> index(int pagina) {
		return paginator.paginate(null, pagina, "dataCadastro", Params.DESC_ORDER, 3);
	}
	
	@Public
	@Get("/blog/paginar/{pagina}")
	public void paginating(int pagina) {
		result.redirectTo(this).index(pagina);
	}
	
	@Public
	@Get("/blog/{artigo.id}")
	public Artigo show(Artigo artigo) {
		return artigoRepo.find(artigo.getId());
	}
	
}


