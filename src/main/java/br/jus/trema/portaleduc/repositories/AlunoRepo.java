package br.jus.trema.portaleduc.repositories;

import java.util.List;

import br.jus.trema.portaleduc.models.Aluno;
import br.jus.trema.portaleduc.models.Turma;
import br.jus.trema.vraptorbridge.repositories.AbstractRepo;

/**
 * 
 * @author Paulo Correa <paulo.correa@tre-ma.gov.br>
 *
 */
public interface AlunoRepo extends AbstractRepo<Aluno> {

	
	public List<Aluno> findByTurma(Turma turma);
	List<Aluno> findByMatricula(String matricula);
	Aluno findByMatriculaSingle(String matricula);
}
