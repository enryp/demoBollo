package com.aruba.demoBollo.service;

import java.util.List;

import com.aruba.demoBollo.beans.VeicoloDto;

public interface VeicoloServiceIntf {
	
	public List<VeicoloDto> getVeicoli(String user);
	
	public VeicoloDto getVeicolo(String targa, String user);

	public VeicoloDto addVeicolo(VeicoloDto v, String user);
	
	public VeicoloDto updateVeicolo(VeicoloDto v, String user);
	
	public void deleteVeicolo(String targa, String user);

}
