package br.jus.trema.portaleduc.repositories.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.jus.trema.portaleduc.models.Aluno;
import br.jus.trema.portaleduc.models.Professor;
import br.jus.trema.portaleduc.models.Turma;
import br.jus.trema.portaleduc.repositories.ProfessorRepo;
import br.jus.trema.vraptorbridge.repositories.impl.AbstractRepoImpl;

/**
 * 
 * @author Paulo Correa <paulo.correa@tre-ma.gov.br>
 *
 */
@Component
public class ProfessorRepoImpl extends AbstractRepoImpl<Professor> implements ProfessorRepo {
	
	public ProfessorRepoImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	protected Criteria getPaginateCriteria(Professor professor) {
		Criteria criteria = super.getPaginateCriteria(professor);
		if (professor.getTurma() != null && professor.getTurma().getId() != null) {
			criteria.createCriteria("turma").add(Restrictions.eq("id", professor.getTurma().getId()));
			//criteria.add(Restrictions.eq("curso", turma.getCurso()));
			//acho que qualquer um dos dois serve, testa um e outro ai,
		}  
	
		return criteria;
	}
	
	@Override
	public List<Professor> findByTurma(Turma turma) {		
		
		String jpql = "select c from Professor c where c.turma = :turma ORDER BY c.nome";		
		TypedQuery<Professor> query = entityManager.createQuery(jpql, Professor.class);
		query.setParameter("turma", turma);
		return query.getResultList();
	}
	
	@Override
	public List<Professor> getHorasProfessorPeriodo(String matricula, Date dataConsulta) {		
		
		String jpql = "select c from Professor c where c.matricula = :matricula and (Year(c.turma.dataInicial) = Year(:dataConsulta))";		
		TypedQuery<Professor> query = entityManager.createQuery(jpql, Professor.class);		
		query.setParameter("dataConsulta", dataConsulta);		
		query.setParameter("matricula", matricula);
		
		return query.getResultList();
	}
	
	@Override
	public List<Professor> findByMatricula(String matricula) {				
		String jpql = "select c from Professor c where c.matricula = :matricula";		
		TypedQuery<Professor> query = entityManager.createQuery(jpql, Professor.class);
		query.setParameter("matricula", matricula);
		return query.getResultList();
	}
}
