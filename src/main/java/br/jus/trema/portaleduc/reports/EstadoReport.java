package br.jus.trema.portaleduc.reports;

import java.util.List;

import br.jus.trema.portaleduc.models.Estado;
import br.jus.trema.vraptorbridge.reports.AbstractReport;

/**
 * 
 * @author Paulo Correa <paulo.correa@tre-ma.gov.br>
 *
 */
public class EstadoReport extends AbstractReport<Estado> {

	public EstadoReport(List<Estado> data, String jasperFileName) {
		super(data, jasperFileName);
	}
	
}
