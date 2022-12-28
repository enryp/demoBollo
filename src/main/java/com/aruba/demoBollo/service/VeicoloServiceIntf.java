package com.aruba.demoBollo.service;

import java.util.List;

import com.aruba.demoBollo.beans.VeicoloDto;

public interface VeicoloServiceIntf {
	
	public List<VeicoloDto> getVeicoli();
	
	public VeicoloDto getVeicolo(Integer id);

	public VeicoloDto addVeicolo(VeicoloDto v);
	
	public void deleteVeicolo(Integer id);

}
