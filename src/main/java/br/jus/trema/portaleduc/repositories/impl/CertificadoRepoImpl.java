package br.jus.trema.portaleduc.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.caelum.vraptor.ioc.Component;
import br.jus.trema.portaleduc.models.Aluno;
import br.jus.trema.portaleduc.models.Certificado;
import br.jus.trema.portaleduc.models.Professor;
import br.jus.trema.portaleduc.models.Turma;
import br.jus.trema.portaleduc.repositories.CertificadoRepo;
import br.jus.trema.vraptorbridge.repositories.impl.AbstractRepoImpl;

/**
 * 
 * @author Paulo Correa <paulo.correa@tre-ma.gov.br>
 *
 */
@Component
public class CertificadoRepoImpl extends AbstractRepoImpl<Certificado> implements CertificadoRepo {
	
	public CertificadoRepoImpl(EntityManager entityManager) {
		super(entityManager);
	}

	public Certificado getCertificado(Turma turma, Aluno aluno) {			
		try {
			String jpql = "select c from Certificado c where c.turma = :turma and c.aluno = :aluno";					
			TypedQuery<Certificado> query = entityManager.createQuery(jpql, Certificado.class);
			query.setParameter("aluno", aluno);
			query.setParameter("turma", turma);							
			return query.getSingleResult();			
		} catch (NoResultException e) {
			System.out.println("retornando nulo");
			return null;		
		}		
	}
	
	public Certificado getCertificado(Turma turma, Professor professor) {			
		try {
			String jpql = "select c from Certificado c where c.turma = :turma and c.professor = :professor";					
			TypedQuery<Certificado> query = entityManager.createQuery(jpql, Certificado.class);
			query.setParameter("professor", professor);
			query.setParameter("turma", turma);							
			return query.getSingleResult();			
		} catch (NoResultException e) {
			System.out.println("retornando nulo");
			return null;		
		}		
	}
	
	
	public List<Certificado> findByID(Turma turma, Aluno aluno) {
	
		String jpql = "select c from Certificado c where c.turma = :turma and c.aluno = :aluno";					
		TypedQuery<Certificado> query = entityManager.createQuery(jpql, Certificado.class);
		query.setParameter("aluno", aluno);
		query.setParameter("turma", turma);	
								
		return query.getResultList();
	}
	
	public List<Certificado> findByIDProfessor(Turma turma, Professor professor) {
		
		String jpql = "select c from Certificado c where c.turma = :turma and c.professor = :professor";					
		TypedQuery<Certificado> query = entityManager.createQuery(jpql, Certificado.class);
		query.setParameter("professor", professor);
		query.setParameter("turma", turma);	
								
		return query.getResultList();
	}
	
	public Certificado findByCodigo(String codigo) {
		try {
			String jpql = "select c from Certificado c where c.codigo = :codigo";					
			TypedQuery<Certificado> query = entityManager.createQuery(jpql, Certificado.class);
			query.setParameter("codigo", codigo);											
			return query.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("retornando nulo");
			return null;		
		}	
	}
}
