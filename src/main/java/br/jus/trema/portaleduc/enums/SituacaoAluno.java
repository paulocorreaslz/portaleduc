package br.jus.trema.portaleduc.enums;

import lombok.Getter;

@Getter
public enum SituacaoAluno {

	A("Aprovado"),
	D("Desistiu"),
	E("Evadiu"),
	R("Reprovado");
	
	private final String value;
	
	private SituacaoAluno(String value) {
		this.value = value;
	}
	
	public String getName() {
		return name();
	}
}
