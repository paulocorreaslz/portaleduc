package br.jus.trema.portaleduc.enums;

import lombok.Getter;

@Getter
public enum TipoCurso {

	D("Distância"),
	P("Presencial"),
	S("Semi-Presencial");
	
	private final String value;
	
	private TipoCurso(String value) {
		this.value = value;
	}
	
	public String getName() {
		return name();
	}
}
