package com.aruba.demoBollo.model;

import java.util.Arrays;

import javax.persistence.AttributeConverter;

import com.aruba.demoBollo.beans.TipoVeicolo;

public class TipoVeicoloConverter implements AttributeConverter<TipoVeicolo, String> {

	@Override
	public String convertToDatabaseColumn(TipoVeicolo attribute) {
		return attribute.getKey();
	}

	@Override
	public TipoVeicolo convertToEntityAttribute(String dbData) {
		return Arrays.asList(TipoVeicolo.values()).stream().filter(v -> v.getKey().equalsIgnoreCase(dbData)).findFirst().orElse(TipoVeicolo.AUTO);
	}

}


