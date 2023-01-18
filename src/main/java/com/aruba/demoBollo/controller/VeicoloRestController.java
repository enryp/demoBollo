package com.aruba.demoBollo.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aruba.demoBollo.beans.BolloDto;
import com.aruba.demoBollo.beans.TipoVeicolo;
import com.aruba.demoBollo.beans.VeicoloDto;
import com.aruba.demoBollo.service.VeicoloServiceImpl;



@RestController
//@CrossOrigin(origins = {"http://localhost:8080"})
@RequestMapping("/api")
public class VeicoloRestController {
	
	Logger log = LoggerFactory.getLogger(VeicoloRestController.class);
	
	@Autowired
	private VeicoloServiceImpl veicoloService;
	
	@GetMapping("/cars")
	public ResponseEntity<List<VeicoloDto>> getVeicoli(@AuthenticationPrincipal Jwt principal) {
		if(principal != null) {
			String idString = principal.getSubject();
			log.debug("#### --> "+idString);
		}
		List<VeicoloDto> vList = veicoloService.getVeicoli(principal.getSubject());
		return ResponseEntity.ok(vList);
	}
	
	@GetMapping("/car/{targa}")
	public ResponseEntity<VeicoloDto> getVeicolo(@PathVariable() String targa, @AuthenticationPrincipal Jwt principal) {
		VeicoloDto veicolo = veicoloService.getVeicolo(targa, principal.getSubject());
		return ResponseEntity.ok(veicolo);
	}
	
	@GetMapping("/car/{targa}/{tipo}/bollo")
	public ResponseEntity<BolloDto> getBolloVeicolo(@PathVariable() String targa, @PathVariable() String tipo, @AuthenticationPrincipal Jwt principal) {
		TipoVeicolo tipoVeicolo = TipoVeicolo.valueOf(tipo);
		BolloDto bollo = veicoloService.retrieveBollo(targa, tipoVeicolo);
		return ResponseEntity.ok(bollo);
	}
	
	@PutMapping("/car/{targa}")
	public ResponseEntity<VeicoloDto> updateVeicolo(@Valid @RequestBody VeicoloDto vDto, @PathVariable() String targa, @AuthenticationPrincipal Jwt principal) {
		if(!vDto.getTarga().toUpperCase().equals(targa.toUpperCase())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		VeicoloDto veicolo = veicoloService.updateVeicolo(vDto, principal.getSubject());
		return ResponseEntity.ok(veicolo);
	}
	
	@PostMapping("/car")
	public ResponseEntity<VeicoloDto> addVeicolo(@Valid @RequestBody VeicoloDto vDto, @AuthenticationPrincipal Jwt principal) {
		VeicoloDto veicolo = veicoloService.addVeicolo(vDto, principal.getSubject());
		return ResponseEntity.status(HttpStatus.CREATED).body(veicolo);
	}
	
	@DeleteMapping("/car/{targa}")
	public ResponseEntity<String> deleteVeicolo(@PathVariable() String targa, @AuthenticationPrincipal Jwt principal) {
		veicoloService.deleteVeicolo(targa, principal.getSubject());
		return ResponseEntity.ok().build();
	}
}
