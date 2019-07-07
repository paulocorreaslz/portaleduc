package br.jus.trema.portaleduc.enums;

import lombok.Getter;

@Getter
public enum TipoProfessor {

	A("Acompanhamento"),
	C("Conteudo"),
	T("Técnico");
	
	private final String value;
	
	private TipoProfessor(String value) {
		this.value = value;
	}
	
	public String getName() {
		return name();
	}
}
