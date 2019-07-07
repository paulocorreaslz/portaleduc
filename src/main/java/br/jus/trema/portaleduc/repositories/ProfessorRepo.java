package br.jus.trema.portaleduc.repositories;

import java.util.Date;
import java.util.List;

import br.jus.trema.portaleduc.models.Professor;
import br.jus.trema.portaleduc.models.Turma;
import br.jus.trema.vraptorbridge.repositories.AbstractRepo;

/**
 * 
 * @author Paulo Correa <paulo.correa@tre-ma.gov.br>
 *
 */
public interface ProfessorRepo extends AbstractRepo<Professor> {

	public List<Professor> findByTurma(Turma turma);

	List<Professor> getHorasProfessorPeriodo(String matricula, Date dataConsulta);

	List<Professor> findByMatricula(String matricula);
}
