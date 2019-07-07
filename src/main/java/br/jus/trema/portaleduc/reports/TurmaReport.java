package br.jus.trema.portaleduc.reports;

import java.util.List;

import br.jus.trema.portaleduc.models.Turma;
import br.jus.trema.vraptorbridge.reports.AbstractReport;

/**
 * 
 * @author Paulo Correa <paulo.correa@tre-ma.gov.br>
 *
 */
public class TurmaReport extends AbstractReport<Turma> {

	public TurmaReport(List<Turma> data, String jasperFileName) {
		super(data, jasperFileName);
	}
}
