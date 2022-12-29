package com.aruba.demoBollo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aruba.demoBollo.beans.VeicoloDto;
import com.aruba.demoBollo.model.Veicolo;
import com.aruba.demoBollo.repository.VeicoloRepository;
import com.aruba.demoBollo.service.VeicoloServiceImpl;



@RestController
@RequestMapping("/stawapp/rest")
public class VeicoloRestController {
	
	Logger log = LoggerFactory.getLogger(VeicoloRestController.class);
	
	@Autowired
	private VeicoloServiceImpl veicoloService;
	
	@GetMapping("/veicoli")
	public List<VeicoloDto> getVeicoli(@AuthenticationPrincipal Jwt principal) {
		if(principal != null) {
			String idString = principal.getSubject();
			log.debug("#### --> "+idString);
		}
		List<VeicoloDto> vList = veicoloService.getVeicoli(principal.getSubject());
		return vList;
	}
	
	@GetMapping("/veicolo/{targa}")
	public VeicoloDto getVeicolo(@PathVariable() String targa, @AuthenticationPrincipal Jwt principal) {
		VeicoloDto veicolo = veicoloService.getVeicolo(targa, principal.getSubject());
		return veicolo;
	}
	
	@PostMapping("/add")
	public VeicoloDto addVeicolo(@RequestBody VeicoloDto vDto) {
		VeicoloDto veicolo = veicoloService.addVeicolo(vDto);
		return veicolo;
	}
	
	@DeleteMapping("/veicolo/{id}")
	public String deleteVeicolo(@PathVariable() Integer id) {
		veicoloService.deleteVeicolo(id);
		return ResponseEntity.ok().build().toString();
	}
}
