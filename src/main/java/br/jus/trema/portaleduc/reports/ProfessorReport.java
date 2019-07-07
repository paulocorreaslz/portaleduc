package br.jus.trema.portaleduc.reports;

import java.util.List;

import br.jus.trema.portaleduc.models.Professor;
import br.jus.trema.vraptorbridge.reports.AbstractReport;

/**
 * 
 * @author Paulo Correa <paulo.correa@tre-ma.gov.br>
 *
 */
public class ProfessorReport extends AbstractReport<Professor> {

	public ProfessorReport(List<Professor> data, String jasperFileName) {
		super(data, jasperFileName);
	}
}
