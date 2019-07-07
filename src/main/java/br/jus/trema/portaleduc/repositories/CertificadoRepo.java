package br.jus.trema.portaleduc.repositories;

import java.util.List;

import br.jus.trema.portaleduc.models.Aluno;
import br.jus.trema.portaleduc.models.Certificado;
import br.jus.trema.portaleduc.models.Professor;
import br.jus.trema.portaleduc.models.Turma;
import br.jus.trema.vraptorbridge.repositories.AbstractRepo;

/**
 * 
 * @author Paulo Correa <paulo.correa@tre-ma.gov.br>
 *
 */
public interface CertificadoRepo extends AbstractRepo<Certificado> {

	public Certificado getCertificado(Turma turma, Aluno aluno);
	public Certificado getCertificado(Turma turma, Professor professor);
	public List<Certificado> findByID(Turma turma, Aluno aluno);
	public List<Certificado> findByIDProfessor(Turma turma, Professor professor);
	public Certificado findByCodigo(String codigo);
	
}
