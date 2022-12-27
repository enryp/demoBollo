package com.aruba.demoBollo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Veicolo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "targa")
	private String targa;
	@Column(name = "tipo")
	private String tipo;
	@Column(name = "codice_fiscale")
	private Integer codiceFiscale;
	@Column(name = "data")
	private Integer data;
	@Column(name = "cilindrata")
	private Integer cilindrata;
	
	
	


}
