package br.jus.trema.portaleduc.enums;

import lombok.Getter;

@Getter
public enum ValidoAQ {

	S("SIM"),
	N("NÃO");
	
	private final String value;
	
	private ValidoAQ(String value) {
		this.value = value;
	}
	
	public String getName() {
		return name();
	}
}
