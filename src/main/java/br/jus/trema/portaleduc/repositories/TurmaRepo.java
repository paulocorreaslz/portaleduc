package br.jus.trema.portaleduc.repositories;

import java.util.List;

import org.joda.time.DateTime;

import br.jus.trema.portaleduc.models.Aluno;
import br.jus.trema.portaleduc.models.Curso;
import br.jus.trema.portaleduc.models.Turma;
import br.jus.trema.vraptorbridge.repositories.AbstractRepo;

/**
 * 
 * @author Paulo Correa <paulo.correa@tre-ma.gov.br>
 *
 */
public interface TurmaRepo extends AbstractRepo<Turma> {

	List<Turma> findByCurso(Curso curso);
	List<Turma> findByID(Long turmaid);
	List<Turma> findByAluno(Aluno aluno);
	List<Turma> findByData(DateTime dataAtual,DateTime dataAtual7);
	List<Turma> listaTurmasAbertasUltimaSemana();
}
