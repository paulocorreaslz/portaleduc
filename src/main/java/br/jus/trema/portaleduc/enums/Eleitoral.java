package br.jus.trema.portaleduc.enums;

import lombok.Getter;

@Getter
public enum Eleitoral {

	S("SIM"),
	N("NÃO");
	
	private final String value;
	
	private Eleitoral(String value) {
		this.value = value;
	}
	
	public String getName() {
		return name();
	}
}
