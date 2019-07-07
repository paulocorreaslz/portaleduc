package br.jus.trema.portaleduc.repositories;

import java.text.ParseException;
import java.util.List;

import br.jus.trema.portaleduc.models.Curso;
import br.jus.trema.vraptorbridge.repositories.AbstractRepo;

/**
 * 
 * @author Paulo Correa <paulo.correa@tre-ma.gov.br>
 *
 */
public interface CursoRepo extends AbstractRepo<Curso> {

	List<Curso> findByName(String nome, String dataCadastro);

}
