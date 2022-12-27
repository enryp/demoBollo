package com.aruba.demoBollo.repository;

import org.springframework.data.repository.CrudRepository;

import com.aruba.demoBollo.model.Veicolo;



public interface VeicoloRepository extends CrudRepository<Veicolo, Integer>{
	
	public Veicolo findByTarga(String Targa);

}
