package br.jus.trema.portaleduc.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;

import br.com.caelum.vraptor.ioc.Component;
import br.jus.trema.portaleduc.models.Aluno;
import br.jus.trema.portaleduc.models.Curso;
import br.jus.trema.portaleduc.models.Turma;
import br.jus.trema.portaleduc.repositories.TurmaRepo;
import br.jus.trema.vraptorbridge.repositories.impl.AbstractRepoImpl;

/**
 * 
 * @author Paulo Correa <paulo.correa@tre-ma.gov.br>
 *
 */
@Component
public class TurmaRepoImpl extends AbstractRepoImpl<Turma> implements TurmaRepo {
	
	public TurmaRepoImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	protected Criteria getPaginateCriteria(Turma turma) {
		Criteria criteria = super.getPaginateCriteria(turma);
		if (turma.getCurso() != null && turma.getCurso().getId() != null) {
			criteria.createCriteria("curso").add(Restrictions.eq("id", turma.getCurso().getId()));
			//criteria.add(Restrictions.eq("curso", turma.getCurso()));
			//acho que qualquer um dos dois serve, testa um e outro ai,
		}  
	
		if (turma.getAlunos() != null && !turma.getAlunos().isEmpty()) {
			String matricula = turma.getAlunos().get(0).getMatricula();
			criteria.createAlias("alunos", "alunosAlias");
			criteria.add(Restrictions.eq("alunosAlias.matricula", matricula));
		}
	
		if (turma.getProfessores() != null && !turma.getProfessores().isEmpty()) {
			String matricula = turma.getProfessores().get(0).getMatricula();
			criteria.createAlias("professores", "professoresAlias");
			criteria.add(Restrictions.eq("professoresAlias.matricula", matricula));
		}
		
		return criteria;
	}	

	@Override
	public List<Turma> findByCurso(Curso curso) {		
		
		String jpql = "select c from Turma c where c.curso = :curso";		
		TypedQuery<Turma> query = entityManager.createQuery(jpql, Turma.class);
		query.setParameter("curso", curso);
		return query.getResultList();
	}
	
	@Override
	public List<Turma> findByID(Long turmaid) {		
		
		String jpql = "select c from Turma c where c.id = :id";		
		TypedQuery<Turma> query = entityManager.createQuery(jpql, Turma.class);
		query.setParameter("id", turmaid);
		return query.getResultList();
	}

	@Override
	public List<Turma> findByAluno(Aluno aluno) {			
		String jpql = "select c from Turma c where c.aluno = :aluno";		
		TypedQuery<Turma> query = entityManager.createQuery(jpql, Turma.class);
		query.setParameter("id", aluno);
		return query.getResultList();
	}

	@Override
	public List<Turma> findByData(DateTime dataAtual,DateTime dataAtual7) {
		String jpql = "select c from Turma c where c.dataInicial between :dataAtual and :dataAtual7";
		TypedQuery<Turma> query = entityManager.createQuery(jpql, Turma.class);		
		query.setParameter("dataAtual", dataAtual);
		query.setParameter("dataAtual7", dataAtual7);
		return query.getResultList();
	}

	@Override
	public List<Turma> listaTurmasAbertasUltimaSemana() {		
		DateTime dataAtual = new DateTime().now();
		DateTime dataAtual7 = dataAtual.plusDays(7);
		return findByData(dataAtual, dataAtual7);
	}
	
}
