package br.jus.trema.portaleduc.enums;

import br.jus.trema.vraptorbridge.authorization.annotations.PermissionRole;

/**
 * 
 * @author Paulo Correa <paulo.correa@tre-ma.gov.br>
 *
 */
public abstract class AppRole extends PermissionRole {
	
	public final static String ADMINISTRADOR = "ADMINISTRADOR";
	public final static String ADMIN = "ADMIN";
	public final static String ALUNO = "ALUNO";
	public final static String PROFESSOR = "PROFESSOR";
	public final static String SECAP = "SECAP";
	
	
}
