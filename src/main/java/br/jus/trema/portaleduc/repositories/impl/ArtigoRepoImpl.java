package br.jus.trema.portaleduc.repositories.impl;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDateTime;

import br.com.caelum.vraptor.ioc.Component;
import br.jus.trema.portaleduc.models.Artigo;
import br.jus.trema.portaleduc.repositories.ArtigoRepo;
import br.jus.trema.vraptorbridge.repositories.impl.AbstractRepoImpl;

/**
 * 
 * @author Paulo Correa <paulo.correa@tre-ma.gov.br>
 *
 */
@Component
public class ArtigoRepoImpl extends AbstractRepoImpl<Artigo> implements ArtigoRepo {
	
	public ArtigoRepoImpl(EntityManager entityManager) {
		super(entityManager);
	}
	
	/*
	@Override
	protected Criteria getPaginateCriteria(Artigo example) {	
		Criteria criteria = Restrictions.conjunction();
		if (example.getTitulo() != null) {
			criteria.add(Restrictions.like("titulo", example.getTitulo(), MatchMode.ANYWHERE).ignoreCase());
		}
		if (example.getDataCadastro() != null) {
			LocalDateTime dataIni = example.getDataCadastro().withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0);
			LocalDateTime dataFim = example.getDataCadastro().withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59);
			criteria.add(Restrictions.between("dataCadastro", dataIni, dataFim));
		}
		return criteria;
	}
	*/
}
