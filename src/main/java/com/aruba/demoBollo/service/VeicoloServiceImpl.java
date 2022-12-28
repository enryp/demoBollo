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
	public List<VeicoloDto> getVeicoli() {
		List<Veicolo> veicoli = veicoloRepository.findAll();
		return veicoli.stream().map(veicoloMapper::getDto).collect(Collectors.toList());
	}

	@Override
	public VeicoloDto getVeicolo(Integer id) {
		Veicolo v = veicoloRepository.findById(id).get();
		return veicoloMapper.getDto(v);
	}

	@Override
	public VeicoloDto addVeicolo(VeicoloDto vDto) {
		Veicolo v = veicoloRepository.save(veicoloMapper.getEntity(vDto));
		return veicoloMapper.getDto(v);
	}

	@Override
	public void deleteVeicolo(Integer id) {
		veicoloRepository.deleteById(id);
	}

}
