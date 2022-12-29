package com.aruba.demoBollo.service;

import java.util.List;

import com.aruba.demoBollo.beans.VeicoloDto;

public interface VeicoloServiceIntf {
	
	public List<VeicoloDto> getVeicoli(String user);
	
	public VeicoloDto getVeicolo(String targa, String user);

	public VeicoloDto addVeicolo(VeicoloDto v);
	
	public void deleteVeicolo(Integer id);

}
