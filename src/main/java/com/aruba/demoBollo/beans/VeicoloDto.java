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
public class VeicoloDto {
	

	@Pattern(regexp = "^[a-zA-Z]{2}[0-9]{3,4}[a-zA-Z]{2}$")
	private String targa;
	
	private String tipo;
	
	@Pattern(regexp = "^[A-Za-z]{6}[0-9]{2}[A-Za-z]{1}[0-9]{2}[A-Za-z]{1}[0-9]{3}[A-Za-z]{1}$")
	private String codiceFiscale;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private java.util.Date immatricolazione;
	
	private Integer cilindrata;
	

}
