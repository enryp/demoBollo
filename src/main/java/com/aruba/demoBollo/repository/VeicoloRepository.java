package com.aruba.demoBollo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.aruba.demoBollo.model.Veicolo;



public interface VeicoloRepository extends JpaRepository<Veicolo, Integer>{
	
	

}
