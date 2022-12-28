package com.aruba.demoBollo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	
	@Autowired
	private VeicoloServiceImpl veicoloService;
	
	@GetMapping("/veicoli")
	public List<VeicoloDto> getVeicoli() {
		List<VeicoloDto> vList = veicoloService.getVeicoli();
		return vList;
	}
	
	@GetMapping("/veicolo/{id}")
	public VeicoloDto getVeicolo(@PathVariable() Integer id) {
		VeicoloDto veicolo = veicoloService.getVeicolo(id);
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
