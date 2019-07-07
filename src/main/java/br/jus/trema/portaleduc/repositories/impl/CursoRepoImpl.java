package br.jus.trema.portaleduc.repositories.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.joda.time.DateTime;

import br.com.caelum.vraptor.ioc.Component;
import br.jus.trema.portaleduc.models.Curso;
import br.jus.trema.portaleduc.repositories.CursoRepo;
import br.jus.trema.vraptorbridge.repositories.impl.AbstractRepoImpl;

/**
 * 
 * @author Paulo Correa <paulo.correa@tre-ma.gov.br>
 *
 */
@Component
public class CursoRepoImpl extends AbstractRepoImpl<Curso> implements CursoRepo {
	
	public CursoRepoImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<Curso> findByName(String nome, String dataCadastro) {		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			if (dataCadastro != null) {
				cal.setTime(sdf.parse(dataCadastro));
			} else {
				cal.setTime(sdf.parse("00/00/0000"));
				cal.clear();				
			}
			} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String jpql = "select c from Curso c where upper(c.nome) like upper(:nome) or Year(c.dataCadastro) = Year(:dataCadastro)";					
		TypedQuery<Curso> query = entityManager.createQuery(jpql, Curso.class);
		query.setParameter("nome", "%" + nome + "%");
		query.setParameter("dataCadastro", cal);
		return query.getResultList();
	}

}
