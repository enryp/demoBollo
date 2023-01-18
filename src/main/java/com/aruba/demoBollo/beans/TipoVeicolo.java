package com.aruba.demoBollo.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TipoVeicolo {
    AUTO("A"),
    MOTO("M"),
    RIMORCHIO("R");

	@Getter
	private String key;

}
