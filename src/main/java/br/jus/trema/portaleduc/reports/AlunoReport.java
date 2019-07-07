package br.jus.trema.portaleduc.reports;

import java.util.List;

import br.jus.trema.portaleduc.models.Aluno;
import br.jus.trema.vraptorbridge.reports.AbstractReport;

/**
 * 
 * @author Paulo Correa <paulo.correa@tre-ma.gov.br>
 *
 */
public class AlunoReport extends AbstractReport<Aluno> {

	public AlunoReport(List<Aluno> data, String jasperFileName) {
		super(data, jasperFileName);
	}
}
