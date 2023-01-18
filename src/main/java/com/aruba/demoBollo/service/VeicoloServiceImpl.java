package com.aruba.demoBollo.service;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.aruba.demoBollo.beans.BolloDto;
import com.aruba.demoBollo.beans.TipoVeicolo;
import com.aruba.demoBollo.beans.VeicoloDto;
import com.aruba.demoBollo.mappers.VeicoloMapper;
import com.aruba.demoBollo.model.Veicolo;
import com.aruba.demoBollo.repository.VeicoloRepository;

import reactor.core.publisher.Mono;

@Service
public class VeicoloServiceImpl implements VeicoloServiceIntf {
	
	@Autowired
	private VeicoloRepository veicoloRepository;
	
	@Autowired
	private VeicoloMapper veicoloMapper;
	
	
	@Override
	public List<VeicoloDto> getVeicoli(String user) {
		List<Veicolo> veicoli = veicoloRepository.findVeicoli(user);
		return veicoli.stream().map(veicoloMapper::getDto).collect(Collectors.toList());
	}

	@Override
	public VeicoloDto getVeicolo(String targa, String user) {
		Veicolo v = veicoloRepository.findVeicolo(targa,user);
		VeicoloDto veicoloDto = null;
		if(v != null) {
			veicoloDto = veicoloMapper.getDto(v);
		}
		return veicoloDto;
	}

	@Override
	public VeicoloDto addVeicolo(VeicoloDto vDto, String user) {
		Veicolo v = veicoloMapper.getEntity(vDto);
		v.setIdUser(user);
		Veicolo result = veicoloRepository.save(v);
		return veicoloMapper.getDto(result);
	}
	
	@Override
	public VeicoloDto updateVeicolo(VeicoloDto vDto, String user) {
		Veicolo vUpdate = veicoloMapper.getEntity(vDto);
		Veicolo vDb = veicoloRepository.findVeicolo(vDto.getTarga(), user);
		if(vDb != null) {
			vDb.setTipo(vUpdate.getTipo());
			vDb.setCilindrata(vUpdate.getCilindrata());
			vDb.setImmatricolazione(vUpdate.getImmatricolazione());
			vDb.setCodiceFiscale(vUpdate.getCodiceFiscale());
		}
		else {
			//new entity
			vDb = vUpdate;
			vDb.setIdUser(user);
		}
		veicoloRepository.save(vDb);
		return veicoloMapper.getDto(vDb);
	}

	@Override
	public void deleteVeicolo(String targa, String user) {
		veicoloRepository.deleteVeicolo(targa, user);
	}

	@Override
	public BolloDto retrieveBollo(String targa, TipoVeicolo tipo) {
		URI regioneUri = UriComponentsBuilder.fromUriString("https://tassa-auto.sistemapiemonte.it/stawapp/rest/bollo/calcola")
						.queryParam("targa", targa)
						.queryParam("tipo_veicolo", tipo.getKey())
						.build().toUri();
		WebClient webClient = WebClient.create();
		ResponseEntity<BolloDto> response = webClient.get()
								  .uri(regioneUri)
								  .header("Content-Type", "application/json")
								  .retrieve()
								    // Don't treat 404 responses as errors:
								    .onStatus(
								        status -> status.value() == 404,
								        clientResponse -> Mono.empty()
								    )

								    .toEntity(BolloDto.class)
								    .block();
		
		if (response.getStatusCodeValue() == 404) {
			return null;
		} else {
			return response.getBody();
		}

	}

}
