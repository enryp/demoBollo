package com.aruba.demoBollo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aruba.demoBollo.model.Veicolo;



public interface VeicoloRepository extends JpaRepository<Veicolo, String>{
	
	@Query("select v from Veicolo v where v.targa = :targa and v.idUser = :idUser")
    Veicolo findVeicolo(@Param("targa") String targa, @Param("idUser") String idUser);
	
	@Query("select v from Veicolo v where v.idUser = :idUser")
    List<Veicolo> findVeicoli(@Param("idUser") String idUser);
	
	@Transactional
	@Modifying
	@Query("delete from Veicolo v where v.targa = :targa and v.idUser = :idUser")
    void deleteVeicolo(@Param("targa") String targa, @Param("idUser") String idUser);
	

}
