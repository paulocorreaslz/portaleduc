package br.jus.trema.portaleduc.enums;

import lombok.Getter;

@Getter
public enum VersaoMoodle {

	A("17"),
	B("18"),
	C("19"),
	D("20"),
	E("21"),
	F("22"),
	G("2X");
	
	
	private final String value;
	
	private VersaoMoodle(String value) {
		this.value = value;
	}
	
	public String getName() {
		return name();
	}
}
