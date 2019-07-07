package br.jus.trema.portaleduc.repositories.impl;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.ioc.Component;
import br.jus.trema.portaleduc.models.Servidor;
import br.jus.trema.portaleduc.repositories.ServidorRepo;
import br.jus.trema.vraptorbridge.repositories.impl.AbstractRepoImpl;

/**
 * 
 * @author Paulo Correa <paulo.correa@tre-ma.gov.br>
 *
 */
@Component
public class ServidorRepoImpl extends AbstractRepoImpl<Servidor> implements ServidorRepo {
	
	public ServidorRepoImpl(EntityManager entityManager) {
		super(entityManager);
	}

}
