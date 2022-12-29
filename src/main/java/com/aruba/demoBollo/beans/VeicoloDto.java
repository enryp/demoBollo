package com.aruba.demoBollo.beans;


import javax.validation.constraints.Pattern;

import com.aruba.demoBollo.model.Veicolo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VeicoloDto {
	

	@Pattern(regexp = "^[a-zA-Z]{2}[0-9]{3,4}[a-zA-Z]{2}$")
	private String targa;
	
	private String tipo;
	
	@Pattern(regexp = "^[A-Za-z]{6}[0-9]{2}[A-Za-z]{1}[0-9]{2}[A-Za-z]{1}[0-9]{3}[A-Za-z]{1}$")
	private String codiceFiscale;
	
	private java.util.Date immatricolazione;
	
	private Integer cilindrata;
	
	public Veicolo toEntity(VeicoloDto vDto) {
		Veicolo v = new Veicolo();
        return v;
    }

}
