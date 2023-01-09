package com.aruba.demoBollo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aruba.demoBollo.beans.VeicoloDto;
import com.aruba.demoBollo.mappers.VeicoloMapper;
import com.aruba.demoBollo.model.Veicolo;
import com.aruba.demoBollo.repository.VeicoloRepository;

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

}
