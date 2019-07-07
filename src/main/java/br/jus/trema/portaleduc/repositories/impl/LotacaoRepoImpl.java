package br.jus.trema.portaleduc.repositories.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.joda.time.LocalDateTime;

import br.com.caelum.vraptor.ioc.Component;
import br.jus.trema.portaleduc.models.Lotacao;
import br.jus.trema.portaleduc.models.Turma;
import br.jus.trema.portaleduc.repositories.LotacaoRepo;
import br.jus.trema.vraptorbridge.repositories.impl.AbstractRepoImpl;

/**
 * 
 * @author Paulo Correa <paulo.correa@tre-ma.gov.br>
 *
 */
@Component
public class LotacaoRepoImpl extends AbstractRepoImpl<Lotacao> implements LotacaoRepo {
	
	public LotacaoRepoImpl(EntityManager entityManager) {
		super(entityManager);
	}
	
public Lotacao getLotacaoAnterior(String matricula, Turma turma) {
	Calendar dataFormatada = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	try {
		if (turma.getDataInicial() != null) {
			dataFormatada.setTime(sdf.parse(turma.getDataInicial().toString("dd/MM/yyyy")));
		} else {
			dataFormatada.setTime(sdf.parse("00/00/00"));
			dataFormatada.clear();				
		}
		} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	String jpql = "select c from Lotacao c where c.matricula = :matricula and (:dataInicioCurso between c.dataInicioLotacao and c.dataFimLotacao)";					
	TypedQuery<Lotacao> query = entityManager.createQuery(jpql, Lotacao.class);
	query.setParameter("matricula", matricula);
	LocalDateTime data = new LocalDateTime(dataFormatada.getTimeInMillis());	
	query.setParameter("dataInicioCurso", data);		
	return query.getSingleResult();
}

}
