package br.jus.trema.portaleduc.repositories.impl;

import javax.persistence.EntityManager;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;

import br.com.caelum.vraptor.ioc.Component;
import br.jus.trema.portaleduc.models.Pais;
import br.jus.trema.portaleduc.repositories.PaisRepo;
import br.jus.trema.vraptorbridge.repositories.impl.AbstractRepoImpl;

/**
 * 
 * @author Paulo Correa <paulo.correa@tre-ma.gov.br>
 *
 */
@Component
public class PaisRepoImpl extends AbstractRepoImpl<Pais> implements PaisRepo {
	
	public PaisRepoImpl(EntityManager entityManager) {
		super(entityManager);
	}
	
	//@Override
	//protected Criterion getPaginateCriteria(Pais example) {
	//	return Example.create(example).enableLike(MatchMode.ANYWHERE).ignoreCase();
	//}
	
	@Override
	public Pais save(Pais pais) {
		pais.setSigla(pais.getSigla().toUpperCase());
		return super.save(pais);
	}
}
