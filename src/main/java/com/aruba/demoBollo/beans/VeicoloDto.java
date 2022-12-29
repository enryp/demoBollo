package com.aruba.demoBollo.beans;


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
	


	private String targa;
	
	private String tipo;

	private String codiceFiscale;
	
	private java.util.Date immatricolazione;
	
	private Integer cilindrata;
	
	public Veicolo toEntity(VeicoloDto vDto) {
		Veicolo v = new Veicolo();
        return v;
    }

}
