package br.jus.trema.portaleduc.controllers;

import lombok.RequiredArgsConstructor;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.jus.trema.portaleduc.enums.AppRole;
import br.jus.trema.vraptorbridge.authorization.annotations.Permission;

/**
 * 
 * @author Paulo Correa <paulo.correa@tre-ma.gov.br>
 *
 */
@Resource
@RequiredArgsConstructor
@Permission({AppRole.ALL})
public class InicioController {

	@Get("/inicio")
	public void index() {	
		
	}
	
}
