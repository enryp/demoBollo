package com.aruba.demoBollo.beans;


import javax.validation.constraints.Pattern;

import org.apache.coyote.http11.filters.SavedRequestInputFilter;

import com.aruba.demoBollo.model.Veicolo;
import com.fasterxml.jackson.annotation.JsonFormat;

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

/**
 * {
   "codiceFiscale":null,
   "tipoVeicolo":{
      "codice":"A",
      "descrizione":"AUTOVEICOLO"
   },
   "targa":"EN300ZJ",
   "esplicativo":"Pot Kw 96 E5",
   "scadenza":"SETTEMBRE 2023",
   "ultimoGiornoUtile":"31/10/2022",
   "validita":12,
   "totale":267.37,
   "tassa":262.08,
   "sanzione":4.38,
   "interesse":0.91
}
 * 
 *
 */
public class BolloDto {
	
	private String targa;
	
	private String scadenza;
	
	private String ultimoGiornoUtile;
	
	private Integer validita;
	
	private Float totale;
	
	private Float tassa;
	
	private Float sanzione;
	
	private Float interesse;
	
	
	

}
