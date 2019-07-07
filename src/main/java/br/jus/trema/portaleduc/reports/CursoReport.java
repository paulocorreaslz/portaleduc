package br.jus.trema.portaleduc.reports;

import java.util.List;

import br.jus.trema.portaleduc.models.Curso;
import br.jus.trema.vraptorbridge.reports.AbstractReport;

/**
 * 
 * @author Paulo Correa <paulo.correa@tre-ma.gov.br>
 *
 */
public class CursoReport extends AbstractReport<Curso> {

	public CursoReport(List<Curso> data, String jasperFileName) {
		super(data, jasperFileName);
	}
}
