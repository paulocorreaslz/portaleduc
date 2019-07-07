package br.jus.trema.portaleduc.reports;

import java.util.List;

import br.jus.trema.portaleduc.models.Certificado;
import br.jus.trema.vraptorbridge.reports.AbstractReport;

/**
 * 
 * @author Paulo Correa <paulo.correa@tre-ma.gov.br>
 *
 */
public class CertificadoReport extends AbstractReport<Certificado> {

	public CertificadoReport(List<Certificado> data, String jasperFileName) {
		super(data, jasperFileName);
	}

	
}
