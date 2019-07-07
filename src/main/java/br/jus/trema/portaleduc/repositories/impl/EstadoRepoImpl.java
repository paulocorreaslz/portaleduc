package br.jus.trema.portaleduc.repositories.impl;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.ioc.Component;
import br.jus.trema.portaleduc.models.Estado;
import br.jus.trema.portaleduc.repositories.EstadoRepo;
import br.jus.trema.vraptorbridge.repositories.impl.AbstractRepoImpl;

/**
 * 
 * @author Paulo Correa <paulo.correa@tre-ma.gov.br>
 *
 */
@Component
public class EstadoRepoImpl extends AbstractRepoImpl<Estado> implements EstadoRepo {

	public EstadoRepoImpl(EntityManager entityManager) {
		super(entityManager);
	}
	
	@Override
	public Estado save(Estado estado) {
		estado.setSigla(estado.getSigla().toUpperCase());
		return super.save(estado);
	}

}
