package com.aruba.demoBollo.mappers;

import org.springframework.stereotype.Service;

import com.aruba.demoBollo.beans.TipoVeicolo;
import com.aruba.demoBollo.beans.VeicoloDto;
import com.aruba.demoBollo.model.Veicolo;
@Service
public class VeicoloMapper {
	
	public VeicoloDto getDto(Veicolo v) {
		VeicoloDto vDto = new VeicoloDto();
		vDto.setCilindrata(v.getCilindrata());
		vDto.setTarga(v.getTarga());
		vDto.setCodiceFiscale(v.getCodiceFiscale());
		vDto.setImmatricolazione(v.getImmatricolazione());
		vDto.setTipo(v.getTipo().toString());
		return vDto;
	}
	
	public Veicolo getEntity(VeicoloDto vDto) {
		Veicolo vEnt = new Veicolo();
		vEnt.setCilindrata(vDto.getCilindrata());
		vEnt.setTarga(vDto.getTarga());
		vEnt.setCodiceFiscale(vDto.getCodiceFiscale());
		vEnt.setImmatricolazione(vDto.getImmatricolazione());
		vEnt.setTipo(TipoVeicolo.valueOf(vDto.getTipo()));
		return vEnt;
	}
}
