package com.aruba.demoBollo.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="veicolo")
public class Veicolo {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Id
	@Column(name = "targa")
	private String targa;
	
	@Column(name = "tipo")
	private String tipo;
	
	@Column(name = "codice_fiscale")
	private String codiceFiscale;
	
	@Column(name = "immatricolazione")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date immatricolazione;
	
	@Column(name = "cilindrata")
	private Integer cilindrata;
	
	@Column(name = "id_user")
	private String idUser;
	
	
	


}
