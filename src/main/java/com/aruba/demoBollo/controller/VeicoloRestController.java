package com.aruba.demoBollo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aruba.demoBollo.model.Veicolo;
import com.aruba.demoBollo.repository.VeicoloRepository;



@RestController
@RequestMapping("/stawapp/rest")
public class VeicoloRestController {
	@Autowired
	private VeicoloRepository veicoloRepository;
	
	@GetMapping("/veicoli")
	public Iterable<Veicolo> getProdotti() {
		return veicoloRepository.findAll();
	}
	
	@GetMapping("/veicolo/{id}")
	public Veicolo getProdotto(@PathVariable() Integer id) {
		return veicoloRepository.findById(id).get();
	}
	
	@PostMapping("/add")
	public Veicolo add(@RequestBody Veicolo p) {
		return veicoloRepository.save(p);
	}
}
