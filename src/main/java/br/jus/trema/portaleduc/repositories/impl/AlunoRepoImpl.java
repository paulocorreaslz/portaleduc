package br.jus.trema.portaleduc.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.jus.trema.portaleduc.models.Aluno;
import br.jus.trema.portaleduc.models.Turma;
import br.jus.trema.portaleduc.repositories.AlunoRepo;
import br.jus.trema.vraptorbridge.repositories.impl.AbstractRepoImpl;

/**
 * 
 * @author Paulo Correa <paulo.correa@tre-ma.gov.br>
 *
 */
@Component
public class AlunoRepoImpl extends AbstractRepoImpl<Aluno> implements AlunoRepo {
	
	public AlunoRepoImpl(EntityManager entityManager) {
		super(entityManager);
	}
	@Override
	protected Criteria getPaginateCriteria(Aluno aluno) {
		Criteria criteria = super.getPaginateCriteria(aluno);
		if (aluno.getTurma() != null && aluno.getTurma().getId() != null) {
			criteria.createCriteria("turma").add(Restrictions.eq("id", aluno.getTurma().getId()));
			//criteria.add(Restrictions.eq("curso", turma.getCurso()));
			//acho que qualquer um dos dois serve, testa um e outro ai,
		}  
	
		return criteria;
	}
	
	@Override
	public List<Aluno> findByTurma(Turma turma) {		
		
		String jpql = "select c from Aluno c where c.turma = :turma ORDER BY c.nome";		
		TypedQuery<Aluno> query = entityManager.createQuery(jpql, Aluno.class);
		query.setParameter("turma", turma);
		return query.getResultList();
	}

	@Override
	public List<Aluno> findByMatricula(String matricula) {				
		String jpql = "select c from Aluno c where c.matricula = :matricula";		
		TypedQuery<Aluno> query = entityManager.createQuery(jpql, Aluno.class);
		query.setParameter("matricula", matricula);
		return query.getResultList();
	}

	@Override
	public Aluno findByMatriculaSingle(String matricula) {				
		try {
			String jpql = "select c from Aluno c where c.matricula = :matricula";		
			TypedQuery<Aluno> query = entityManager.createQuery(jpql, Aluno.class);
			query.setParameter("matricula", matricula);
			return query.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("retornando nulo");
			return null;		
		}	
	}
	
}
